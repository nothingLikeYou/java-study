# Java IO体系简述



## 按照操作类型分类图

![java-IO(1)](/home/zhangsl/app/java/dev/java-study/img/java-IO(1).jpeg)



##　InputStream

**主要方法**：

```java
public abstract int read() throws IOException;
```

抽象方法,子类必须实现,每次读取流中一个字节...在　FileInputStream　中其调用的是本地方法,由ｃ语言实现,具体实现与操作系统有关．从流中读取下一个字节，返回类型　ｉｎｔ 取值０～２５５　当读取到流结尾时，返回值为－１,如果流中没有数据，ｒｅａｄ　方法会阻塞直到数据到来,流关闭或异常出现．异常出现时会抛出　IOException,是受检异常,调用者必须进行处理.

**一次读取多个字节**

```java
public int read(byte b[]) throws IOException
```

读入的字节放入参数数组b中,第一个字节存入b[0],第二个存入b[1],以此类推,一次最多读入的字节个数为数组b的长度,但实际读入的个数可能小于数组长度,返回值为实际读入的字节个数.如果刚开始读取时已到流结尾,则返回-1,否则,只要数组长度大于0,该方法都会尽力至少读取一个字节,如果流中一个字节都没有,它会阻塞,异常出现时,抛出 IOException, 此方法不是抽象方法, InputStream 有一个默认实现,主要就是循环调用一个字节的 read 方法,但子类,如 FileInputStream 往往有更为高效的实现.

**一次性读取多个字节**

```java
public int read(byte b[], int off, int len) throws IOException
```

更为通用的方法批量读取字节的方法,,内部实际调用 read 抽象方法,每次读取流中一个字节,读入的第一个字节放入 b[off],然后循环遍历,调用 read 抽象方法,最终读取的字节放入 b[] 如果 读取到流结尾,则直接 break ,中断循环..最多读取len个字节  以上方法内部就是调用此方法实现的.如下所示.

```java
 public int read(byte b[]) throws IOException {
        return read(b, 0, b.length);
    }
```

**关闭流,释放资源**

流读取结束后,应该关闭,释放相关系统资源,不管 read 方法是否抛出了异常,都应该调用 close 方法,,所以 close 方法通常是放在 finally 语句块中, close 方法自己可能也会抛出 IOException 但通常可以捕获并忽略.



## OutputStream

**基本方法**

```java
public abstract void write(int b) throws IOException;
```

抽象方法，具体逻辑由子类实现，参数类型为ｉｎｔ 但其实只会用到最低的８位，　FileOutputStream 中会调用本地方法,由c语言实现.

#### 默认实现方法

**批量写入方法**

```java
public void write(byte b[], int off, int len) throws IOException
```

方法内部 遍历 调用 write 抽象方法,从  下标索引 off 开始写入 b[],写入 len 长度字节  

**批量写入方法**

```java
public void write(byte b[]) throws IOException {
        write(b, 0, b.length);
    }
```

看源码可知,是以上方法的重载,直接内部直接调用  write ,写入整个 b[] 长度的字节.

**数据刷新方法**

```java
public void flush() throws IOException
```

将缓冲而未实际写的数据进行实际写入,比如,在 BufferedOutputStream 中,调用flush 方法会将其缓冲区的内容写到其装饰的流中,并调用该流的flush 方法..基类 OutputStream 没有缓冲,flush 方法代码为空,

你可能会认为 FileOutputStream 调用 flush 会强制确保数据保存到硬盘上,但实际上不是这样,FileOutputStream 没有缓冲,没有重写flush方法,调用flush方法没有任何效果,数据只是传递给了操作系统,但操作系统什么时候保存到硬盘上,可以调用FIleOuputStream中的特有方法.

**关闭输出流**

close 方法一般会在flush 方法之后调用,用来释放流占用的系统资源,也是一般放在 finally 语句内调用.



### FileOutputStream

文件写入流 OutputStream 抽象类的具体实现.

#### 构造方法

```java
//构造方法主要逻辑,其他的构造方法内部实际上都是调用此方法
public FileOutputStream(File file, boolean append);
//内部调用 FileOutputStream(File file, boolean append) 方法,append默认为false
public FileOutputStream(String name) throws FileNotFoundException;
//内部调用 FileOutputStream(File file, boolean append) 方法 append默认为false
public FileOutputStream(File file);
//内部调用 FileOutputStream(File file, boolean append) 方法
public FileOutputStream(String name, boolean append)
```

**说明:**

- File 类型的参数 file 和字符串的类型的参数 name 都表示文件路径,路径可以是绝对路径,也可以是相对路径
- 如果文件已存在,append参数指定是追加还是覆盖,true为追加,false覆盖
- new FileOutputStream 对象实际上会打开文件,操作系统会分配相关资源,如果当前用户没有写权限,会抛出SecurityException
- 如果指定的文件是一个目录,或者由于其他原因不能打开文件,会抛出 FileNotFoundException 



#### 例子1:

字符串以字节形式写入文件,,若文件不存在,则新建

```java
   OutputStream outputStream = null;
        try {
            //初始化写入流
            outputStream = new FileOutputStream("/tmp/user/hello.txt");
            //待写入的数据
            String data = "hello, 123, 小林";
            //获取字符串的字节数组数据 utf-8格式
            byte[] bytes = data.getBytes(Charset.forName("UTF-8"));
            //将字节数组写入文件
            outputStream.write(bytes);
            //刷新
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭io流 释放系统资源
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
```


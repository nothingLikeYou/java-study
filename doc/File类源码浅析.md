# File类源码浅析



## 成员变量



```java
 	/**
     * FileSystem对象表示平台的本地文件系统
     */
    private static final FileSystem fs = DefaultFileSystem.getFileSystem();

	/**
     *此抽象路径名的规范化路径名字符串。, 规范化的* pathname字符串使用默认的名称 - 分隔符，并且不包含      *任何重复或冗余的分隔符
     *
     * @serial
     */
    private final String path;

    /**
     * 枚举类型，指示文件路径的状态。
     */
    private static enum PathStatus { INVALID, CHECKED };

    /**
     * 指示文件路径是否无效的标志
     */
    private transient PathStatus status = null;

    /**
     * 此抽象路径名前缀的长度，如果没有前缀，则为零
     */
    private final transient int prefixLength;

    /**
         系统相关的默认名称分隔符。 , *字段初始化为包含system * property <code> file.separator 		</ code>值的第一个字符。, 在UNIX系统上，此*字段的值为<code>'/'</ code>;, 在Microsoft 			Windows系统上，它是<code>'\\'</ code>。
     *
     * @see     java.lang.System#getProperty(java.lang.String)
     */
    public static final char separatorChar = fs.getSeparator();

	 /**
     * 系统相关的默认名称分隔符，为方便起见，表示为*字符串。, 该字符串包含单个字符，即
     * <code>{@link #separatorChar}</code>.
     */
    public static final String separator = "" + separatorChar;
    
    /**
     * 与系统相关的路径分隔符。 , *初始化此字段以包含system * property <code> path.separator </ code>的值的第一个字符。, 此字符用于*分隔作为<em>路径列表</ em>给出的文件序列中的文件名。 , *在UNIX系统上，此字符为<code>'：'</ code>;, 在Microsoft Windows系统上，它*是<code>';'</ code>。
     *
     * @see     java.lang.System#getProperty(java.lang.String)
     */
    public static final char pathSeparatorChar = fs.getPathSeparator();

    /**
     * 与系统相关的路径分隔符，表示为字符串
     * for convenience.  This string contains a single character, namely
     * <code>{@link #pathSeparatorChar}</code>.
     */
    public static final String pathSeparator = "" + pathSeparatorChar;

```



## 构造方法

```java
//已经规范化的路径名字符串的内部构造函数
private File(String pathname, int prefixLength);
private File(String child, File parent);
//根据路径名初始化File实例
public File(String pathname);

public File(String parent, String child);
public File(URI uri);
```


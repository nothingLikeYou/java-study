package com.zhangshaolin;

import java.io.*;

/**
 * @program: java-study
 * @description: 测试数据写入文件
 * @author: zhangshaolin
 * @create: 2018-09-28 12:35
 **/
public class FileStreamTest2 {

    public static void main(String[] args) {

        try {
            File file = new File("/tmp/a.txt");
            //构建FileOutputStream对象 文件不存在会自动创建
            OutputStream os = new FileOutputStream(file);
            //构建OutputStreamWriter对象 参数可以指定编码 默认为操作系统默认编码 win上为gbk linux为utf-8
            OutputStreamWriter writer = new OutputStreamWriter(os, "UTF-8");
            //写入到缓冲区
            writer.append("中文输入");
            //换行
            writer.append("\r\n");
            writer.append("English");
            //刷新缓冲区,写入到文件,如果下面已经没有写入的内容了,直接close也会写入
            writer.close();
            //关闭输出流 释放系统资源
            os.close();

            //构建FileInputStream
            InputStream is = new FileInputStream(file);
            //构建 InputStreamReader 编码与写入相同
            InputStreamReader reader = new InputStreamReader(is, "UTF-8");

            StringBuffer sb = new StringBuffer();
            while (reader.ready()) {
                //转成char 加到StringBuffer 中
                sb.append(((char) reader.read()));
            }
            System.out.println(sb.toString());
            //关闭读取流
            reader.close();
            //关闭输入流 释放系统资源
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

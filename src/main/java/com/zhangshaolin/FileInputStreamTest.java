package com.zhangshaolin;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: java-study
 * @description: 文件读取流测试
 * @author: zhangshaolin
 * @create: 2018-10-14 08:39
 **/
public class FileInputStreamTest {

    public static void main(String[] args) {

        InputStream inputStream;
        try {
            //初始化文件读取流
            inputStream = new FileInputStream("/tmp/user/hello.txt");
            //初始化字节数组 存放读取的字节数
            byte[] buf = new byte[1024];
            //读取文件字节数据 放入buf 返回实际读取的字节数
            int bytesRead = inputStream.read(buf);
            //转化为string 输出
            String data = new String(buf, 0, bytesRead, "UTF-8");
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.zhangshaolin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @program: java-study
 * @description: 文件读取流测试3
 * @author: zhangshaolin
 * @create: 2018-10-14 09:06
 **/
public class FileInputStreamTest3 {

    public static void main(String[] args) {
        InputStream inputStream;
        try {
            //初始化文件读取流
            inputStream = new FileInputStream("/tmp/user/hello.txt");
            //初始化字节数组 存放读取的字节数
            byte[] buf = new byte[1024];
            int off = 0;
            int byteRead = 0;
            while ((byteRead = inputStream.read(buf, off, 1024 - off)) != -1) {
                off += byteRead;
            }
            String data = new String(buf, 0, off, Charset.forName("UTF-8"));
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

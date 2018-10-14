package com.zhangshaolin;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * @program: java-study
 * @description: 文件写入流测试
 * @author: zhangshaolin
 * @create: 2018-10-12 17:33
 **/
public class FileOutputStreamTest {

    public static void main(String[] args) {

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
    }
}

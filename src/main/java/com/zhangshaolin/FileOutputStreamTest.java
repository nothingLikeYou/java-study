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
            outputStream = new FileOutputStream("/tmp/user/hello.txt");
            String data = "hello, 123, 小林";
            byte[] bytes = data.getBytes(Charset.forName("UTF-8"));
            outputStream.write(bytes);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

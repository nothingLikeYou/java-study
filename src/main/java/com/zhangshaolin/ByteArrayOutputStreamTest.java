package com.zhangshaolin;

import java.io.*;

/**
 * @program: java-study
 * @description: ByteArrayOutputStream  写文件测试
 * @author: zhangshaolin
 * @create: 2018-10-16 13:02
 **/
public class ByteArrayOutputStreamTest {

    public static void main(String[] args) {

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("/tmp/hello.txt");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int byteRead = 0;
            while ((byteRead = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, byteRead);
            }
            String data = outputStream.toString("UTF-8");
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

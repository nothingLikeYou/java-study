package com.zhangshaolin;

import java.io.*;

/**
 * @program: java-study
 * @description: 测试数据写入文件 首先创建文件test.txt，并把给定的数字以二进制形式写进该文件(可能存在乱码)，同时输出到控制台上。
 * @author: zhangshaolin
 * @create: 2018-09-28 12:23
 **/
public class FileStreamTest {

    public static void main(String[] args) {
        try {
            byte bwrite[] = {11, 21, 3, 40, 5};
            OutputStream os = new FileOutputStream("/tmp/text.txt");
            for (int i = 0; i < bwrite.length; i++) {
                os.write(bwrite[i]);
            }
            os.close();

            InputStream is = new FileInputStream("/tmp/text.txt");
            int size = is.available();

            for (int i = 0; i < size; i++) {
                System.out.println(((char) is.read()) + " ");
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

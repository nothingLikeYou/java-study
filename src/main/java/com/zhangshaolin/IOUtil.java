package com.zhangshaolin;

import java.io.*;

/**
 * @program: java-study
 * @description: IO工具代码 实际开发中 会使用apache的common-io库
 * @author: zhangshaolin
 * @create: 2018-10-16 17:08
 **/
public class IOUtil {

    /**
     * 复制输入流的内容到输出流
     *
     * @param inputStream
     * @param outputStream
     */
    public static void copy(InputStream inputStream, OutputStream outputStream) {
        byte[] buf = new byte[4096];
        int byteRead = 0;
        try {
            while ((byteRead = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, byteRead);
            }
        } catch (IOException e) {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * 将文件读入字节数组
     *
     * @param fileName 文件名
     * @return
     */
    public static byte[] readFileToByteArray(String fileName) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(fileName);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            copy(inputStream, outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将字节数组写到文件
     *
     * @param fileName
     * @param data
     */
    public static void writeByteArrayToFile(String fileName, byte[] data) {
        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(fileName);
            outputStream.write(data);
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

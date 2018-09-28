package com.zhangshaolin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @program: java-study
 * @description: 使用 BufferedReader 在控制台读取字符串
 * @author: zhangshaolin
 * @create: 2018-09-28 11:46
 **/
public class BRReadLines {

    public static void main(String[] args) {
        //使用 System.in 创建 BufferedReader
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        System.out.println("Enter lines of text.");
        System.out.println("Enter 'end' to quit.");
        do {
            try {
                str = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(str);
        } while (!"end".equals(str));
    }
}

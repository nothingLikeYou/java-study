package com.zhangshaolin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @program: java-study
 * @description: 使用 BufferReader 在控制台读取字符
 * @author: zhangshaolin
 * @create: 2018-09-28 11:38
 **/
public class BRRead {

    public static void main(String[] args) {
        char c = 0;
        //使用 System.in 创建 BufferdReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符,按下 'q' 键退出.");
        //读取字符
        do {
            try {
                c = ((char) br.read());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (c != 'q');
    }
}

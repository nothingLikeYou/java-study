package com.zhangshaolin;

import java.io.File;

/**
 * @program: java-study
 * @description: 读取目录 ;列出目录中子目录与文件(没有递归子目录)
 * @author: zhangshaolin
 * @create: 2018-09-28 13:11
 **/
public class DirList {

    public static void main(String[] args) {
        String dirName = "/tmp";
        File dir = new File(dirName);
        //判断是否为目录
        if (dir.isDirectory()) {
            System.out.println("目录" + dirName);
            String[] list = dir.list();
            for (int i = 0; i < list.length; i++) {
                File file = new File(dirName + "/" + list[i]);
                if (file.isDirectory()) {
                    System.out.println(list[i] + " 是一个目录");
                } else if (file.isFile()) {
                    System.out.println(list[i] + " 是一个文件");
                }
            }
        } else {
            System.out.println(dirName + " 不是一个目录");
        }
    }
}

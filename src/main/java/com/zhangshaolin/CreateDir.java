package com.zhangshaolin;

import java.io.File;

/**
 * @program: java-study
 * @description: 创建目录
 * @author: zhangshaolin
 * @create: 2018-09-28 13:00
 **/
public class CreateDir {

    /**
     * File类中方法 mkdir() 创建一个目录,成功返回true,是否则返回false,失败表明File对象指定的路径已经存在,或者由于整个路径还不存在,该目录无法创建
     * File类中方法 mkdirs() 创建一个目录,以及它的所有父级目录
     *
     * @param args
     */
    public static void main(String[] args) {
        String dirName = "/tmp/user/java/bin";
        File dir = new File(dirName);
        //创建目录
        System.out.println(dir.mkdirs());
    }
}

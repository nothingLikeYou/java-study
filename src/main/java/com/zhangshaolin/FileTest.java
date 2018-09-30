package com.zhangshaolin;

import java.io.File;
import java.io.IOException;

/**
 * @program: java-study
 * @description: File类功能测试
 * @author: zhangshaolin
 * @create: 2018-09-29 11:22
 **/
public class FileTest {

    public static void main(String[] args) throws IOException {

        /*********************构造函数测试**************************/
        //根据路径名初始化File实例,如果按照路径文件/目录不存在,依然会构造出File对象,只是File.exited()为false,即文件不存在
        File file1 = new File("/tmp/user");
        //toString 最终调用的是 getPath() 返回文件的 path路径
        System.out.println("file1路径为: " + file1.toString());

        //根据父目录,子目录 获取完整文件 实例 如果按照路径文件/目录不存在,依然会构造出File对象,只是File.exited()为false,即文件不存在
        File file2 = new File("/tmp", "user");
        System.out.println("file2路径为: " + file2.toString());

        //第二种构造的重载形式
        File file3 = new File(file2, "lalla");
        System.out.println("file3路径为: " + file3.toString());
        /*********************构造函数测试**************************/

        /*********************成员方法测试**************************/

        //获取最后节点 文件/目录名称
        String name = file1.getName();
        System.out.println("文件名称为: " + name);

        //获取文件/目录父级目录名称 如:/tmp
        String parent = file1.getParent();
        System.out.println("文件/目录父级目录名称为: " + parent);

        //获取文件/目录父级目录 并以File实例包装一层返回
        File parentFile = file1.getParentFile();
        System.out.println("父级目录文件的路径为:" + parentFile.toString());

        //获取文件的路径  即成员变量
        String path = file1.getPath();
        System.out.println("文件的路径为: " + path);

        //判断文件是否为绝对路径
        boolean absolute = file1.isAbsolute();
        System.out.println("file1是绝对路径吗? " + absolute);

        //获取文件绝对路径
        String absolutePath = file1.getAbsolutePath();
        System.out.println("文件绝对路径: " + absolutePath);

        //获取文件绝对路径 再用File包装返回
        File absoluteFile = file1.getAbsoluteFile();
        System.out.println("获取文件绝对路径:" + absoluteFile.toString());

        try {
            String canonicalPath = file1.getCanonicalPath();
            System.out.println("??? " + canonicalPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File canonicalFile = file1.getCanonicalFile();
        System.out.println("??? " + canonicalFile.toString());

        //判断程序对文件是否有可读权限
        boolean canRead = file1.canRead();
        System.out.println("程序对文件是否有可读权限? " + canRead);

        //判断程序对文件是否有可写权限
        boolean canWrite = file1.canWrite();
        System.out.println("程序对文件是否有可写权限" + canWrite);

        //判断文件是否存在
        boolean exists = file1.exists();
        System.out.println("文件存在吗? " + exists);

        //判断是否是目录
        boolean directory = file1.isDirectory();
        System.out.println("是目录吗? " + directory);

        //判断是否是文件
        boolean file = file1.isFile();
        System.out.println("是文件吗?" + file);

        //判断是否是隐藏文件
        boolean hidden = file1.isHidden();
        System.out.println("是隐藏文件吗? " + hidden);


        /*********************成员方法测试**************************/
    }
}

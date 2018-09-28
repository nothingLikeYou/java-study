package com.zhangshaolin;

import java.io.File;

/**
 * @program: java-study
 * @description: 删除目录或文件
 * @author: zhangshaolin
 * @create: 2018-09-28 13:21
 **/
public class DeleteFileDemo {

    public static void main(String[] args) {
        File file = new File("/tmp/user");
        deleteFile(file);
    }

    /**
     * 删除目录或者文件.列出目录中所有的文件,判断若是目录.则递归再删除,最终删除根目录
     * 如果所有文件都删除成功则返回true, 有一个文件删除失败就停止删除并返回false
     *
     * @param file
     */
    public static boolean deleteFile(File file) {
        boolean flag = false;
        if (file.exists()) {
            //判断是目录还是文件
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File file1 : files) {
                        //递归删除文件或目录
                        deleteFile(file1);
                    }
                }
            } else if (file.isFile()) {
                file.delete();
            }
        }
        flag = file.delete();
        return flag;
    }
}

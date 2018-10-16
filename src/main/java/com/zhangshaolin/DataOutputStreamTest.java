package com.zhangshaolin;

import com.zhangshaolin.model.Student;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @program: java-study
 * @description: DataOutputStream 写入学生列表到文件测试
 * @author: zhangshaolin
 * @create: 2018-10-16 14:37
 **/
public class DataOutputStreamTest {

    public static void main(String[] args) {

        //学生列表
        List<Student> students = Arrays.asList(new Student[]{new Student("张三", 18, 80.9d), new Student("李四", 17, 67.5d)});

        writeStudents(students);
    }

    public static void writeStudents(List<Student> students) {
        DataOutputStream outputStream = null;
        try {
            outputStream = new DataOutputStream(new FileOutputStream("/tmp/student.dat"));
            outputStream.writeInt(students.size());
            for (Student student : students) {
                outputStream.writeUTF(student.getName());
                outputStream.writeInt(student.getAge());
                outputStream.writeDouble(student.getScore());
            }
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

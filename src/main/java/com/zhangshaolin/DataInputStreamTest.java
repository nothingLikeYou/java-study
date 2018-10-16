package com.zhangshaolin;

import com.zhangshaolin.model.Student;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: java-study
 * @description: DataInputStream 测试 读取 学生列表文件
 * @author: zhangshaolin
 * @create: 2018-10-16 14:52
 **/
public class DataInputStreamTest {

    public static void main(String[] args) {
        List<Student> students = readStudents();
        System.out.println(students.size());
    }

    public static List<Student> readStudents() {
        DataInputStream inputStream = null;
        List<Student> students = null;
        try {
            inputStream = new DataInputStream(new FileInputStream("/tmp/student.dat"));
            int size = inputStream.readInt();
            students = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                String name = inputStream.readUTF();
                int age = inputStream.readInt();
                double score = inputStream.readDouble();
                Student student = new Student(name, age, score);
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}

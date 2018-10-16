package com.zhangshaolin.model;

/**
 * @program: java-study
 * @description: 学生类
 * @author: zhangshaolin
 * @create: 2018-10-16 14:36
 **/
public class Student {

    private String name;

    private int age;

    private double score;

    public Student(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

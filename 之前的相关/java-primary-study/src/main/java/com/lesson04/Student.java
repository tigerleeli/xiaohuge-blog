package com.lesson04;


public class Student {
    // 姓名
    String name;
    // 年龄
    int age;
    // 性别
    String sex;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, int age) {
        this(name);
        this.age = age;
    }

    public Student(String name, int age, String sex) {
        this(name, age);
        this.sex = sex;
    }
}

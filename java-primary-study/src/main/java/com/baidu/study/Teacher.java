package com.baidu.study;

import com.lesson04_01.Student;

public class Teacher {

    private String name;

    protected String no;

    public int age;

    float money;

    public void study() {
    }

    public static void main(String[] args) {
        // 创建构造者
        Student.Builder builder = new Student.Builder("张三")
                .age(18)
                .sex("女");
        // 构造者创建目标对象
        Student s1 = builder.build();

        // 简约写法
        Student s2 = new Student.Builder("张三")
                .age(18)
                .sex("女")
                .build();
    }
}

package com.lesson04_01;

public class Student {
    String name;
    int age;
    String sex;

    private Student(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.sex = builder.sex;
    }

    public static class Builder {
        String name;
        int age;
        String sex;


        // name是必须得
        public Builder(String name) {
            this.name = name;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    public static void main(String[] args) {
        Student student = new Student.Builder("张三")
                .age(18)
                .sex("女")
                .build();

    }
}

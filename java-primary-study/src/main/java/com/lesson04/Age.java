package com.lesson04;

public class Age {
    // 年龄
    int number = 10;

    // 生日
    Age birthday() {
        sing(); // 等同于 this.sing();
        number++; // 等同于 this.number++;
        return this;
    }

    // 唱生日歌
    void sing() {
        System.out.println("祝你生日快乐...");
    }

    public static void main(String[] args) {
        Age age = new Age();
        age.birthday().birthday().birthday();
        System.out.println("现在年龄为：" + age.number);// 结果为：13
    }
}

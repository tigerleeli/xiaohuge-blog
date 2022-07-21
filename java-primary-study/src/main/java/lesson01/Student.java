package lesson01;

public class Student {
    // 姓名
    String name;

    // 年龄
    int age;

    public float study(String lesson, float hour) {
        System.out.println("姓名：" + name);
        System.out.println("年龄：" + age);
        System.out.println("学习课程：" + lesson);
        System.out.println("学习时长：" + hour);
        // 方法返回多少分钟
        return hour * 60;
    }

    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "小明";
        s1.age = 18;
        float result = s1.study("Java", 1.5f);
        System.out.println("调用study方法返回结果：" + result);
    }
}

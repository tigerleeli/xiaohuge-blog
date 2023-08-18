package lesson08.lesson0801;

// 子类 学生
public class Student extends People {
    // 姓名
    public String name;

    // 默认构造方法(构造器)
    public Student() {
        System.out.println("Student()");
    }

    // 有参构造方法(构造器)
    public Student(String name) {
        this.name = name;
        System.out.println("Student(" + name + ")");
    }
}

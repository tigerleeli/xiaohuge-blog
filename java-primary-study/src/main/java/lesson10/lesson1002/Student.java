package lesson10.lesson1002;


public class Student extends People {
    // 公开字段
    public String name;
    // 受保护字段
    protected String sex;
    // 私有字段
    private int age;

    // 默认构造方法
    public Student() {
    }

    // 有参构造方法
    public Student(String name) {
        this.name = name;
    }

    // 公开方法
    public void setName(String name) {
        this.name = name;
    }

    // 受保护方法
    protected void setSex(String sex) {
        this.sex = sex;
    }

    // 私有方法
    private void setAge(int age) {
        this.age = age;
    }
}

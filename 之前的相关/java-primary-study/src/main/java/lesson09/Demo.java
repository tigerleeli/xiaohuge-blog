package lesson09;

public class Demo {
    public static void main(String[] args) {
        // 第一种 新建类
        Student s1 = new Student();
        s1.getClass();

        // 第二种 调用静态字段或方法
        Student.print();

        try {
            // 第三种 手动加载
            Class clazz1 = Class.forName("lesson09.Student");
            Class clazz = Student.class;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

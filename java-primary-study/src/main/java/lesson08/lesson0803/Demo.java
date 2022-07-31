package lesson08.lesson0803;

public class Demo {
    public static void main(String[] args) {
        // 1-触发类字节码被加载
        Student s1 = new Student();

        // 2-触发类字节码被加载
        String name = Student.name;

        // 3-触发类字节码被加载
        try {
            Class<?> clazz = Class.forName("lesson08.lesson0803.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

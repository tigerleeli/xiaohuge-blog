package lesson10.lesson1006;

import java.lang.reflect.Constructor;

public class Demo {
    public static void main(String[] args) {
        Class<?> c = Student.class;
        try {
            Student s1 = (Student) c.newInstance();
            System.out.println(s1);

            Constructor<?> ctor1 = c.getDeclaredConstructor();
            Student s2 = (Student) ctor1.newInstance();
            System.out.println(s2);

            Constructor<?> ctor2 = c.getDeclaredConstructor(String.class);
            Student s3 = (Student) ctor2.newInstance("张三");
            System.out.println(s3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

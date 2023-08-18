package lesson10.lesson1003;

import lesson10.lesson1002.Student;

import java.lang.reflect.Method;

public class Demo {
    public static void main(String[] args) {
        try {
            Class c = Student.class;
//            Class<?> c = Student.class;
            Method m = c.getMethod("m");  // 警告

        } catch (NoSuchMethodException x) {
            x.printStackTrace();
        }

    }
}

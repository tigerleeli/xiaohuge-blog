package lesson10.lesson1005;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo {
    public static void main(String[] args) {
        try {
            Student s = new Student();
            Class<?> c = s.getClass();
            Method method = c.getDeclaredMethod("setName", String.class, int.class);
            method.invoke(s, "小明", 18);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

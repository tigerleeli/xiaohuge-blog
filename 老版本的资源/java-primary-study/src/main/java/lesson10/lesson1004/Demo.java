package lesson10.lesson1004;

import java.lang.reflect.Field;

public class Demo {
    public static void main(String[] args) {
        try {
            Student s = new Student();
            Class<?> c = s.getClass();

            Field name = c.getDeclaredField("name");
            name.set(s, "小明");

            Field age = c.getDeclaredField("age");
            age.setInt(s, 18);

            Field height = c.getDeclaredField("height");
            //  height.setFloat(s, 180f);  // 错误 私有变量无法赋值

            System.out.println(s);

        } catch (NoSuchFieldException | IllegalAccessException x) {
            x.printStackTrace();
        }
    }
}

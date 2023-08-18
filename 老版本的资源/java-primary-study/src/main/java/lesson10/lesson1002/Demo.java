package lesson10.lesson1002;

import java.lang.reflect.Field;

public class Demo {
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("lesson10.lesson1002.Student"); // 类路径
            Field[] declaredFields = c.getDeclaredFields();

            for (Field field : declaredFields) {
                System.out.println("declaredField：" + field.toGenericString());
            }
            System.out.println();

            Field[] fields = c.getFields();
            for (Field field : fields) {
                System.out.println("field：" + field.toGenericString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

package lesson08.lesson0803;

public class Student {
    public static String name = "姓名";

    static {
        System.out.println("静态作用域，只会调用一次");
    }
}

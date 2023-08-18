package lesson14.lesson1004;

import java.util.List;

public class Demo {
//    public static void printList(List<Object> list) {
//        for (Object elem : list)
//            System.out.println(elem + " ");
//        System.out.println();
//    }

    public static void printList(List<?> list) {
        for (Object elem : list) {
            System.out.print(elem + " ");
        }
    }

    public static void main(String[] args) {
        Class<?> c = new Demo().getClass();
    }
}

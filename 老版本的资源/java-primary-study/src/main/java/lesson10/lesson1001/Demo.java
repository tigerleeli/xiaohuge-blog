package lesson10.lesson1001;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String... args) {
        try {
            Class<?> c = Class.forName("lesson10.lesson1001.Student"); // 类路径
            System.out.format("类名 Class:%n  %s%n%n", c.getCanonicalName()); // 类名
            System.out.format("修饰符 Modifiers:%n  %s%n%n",
                    Modifier.toString(c.getModifiers())); // 修饰符

            System.out.format("泛型参数 Type Parameters:%n");// 泛型参数
            TypeVariable[] tv = c.getTypeParameters();
            if (tv.length != 0) {
                System.out.format("  ");
                for (TypeVariable t : tv)
                    System.out.format("%s ", t.getName());
                System.out.format("%n%n");
            } else {
                System.out.format("  -- No Type Parameters --%n%n");
            }

            System.out.format("接口 Implemented Interfaces:%n"); // 接口
            Type[] intfs = c.getGenericInterfaces();
            if (intfs.length != 0) {
                for (Type intf : intfs)
                    System.out.format("  %s%n", intf.toString());
                System.out.format("%n");
            } else {
                System.out.format("  -- No Implemented Interfaces --%n%n");
            }

            System.out.format("继承路径 Inheritance Path:%n"); // 继承路径 父类->父类的父类...
            List<Class> l = new ArrayList<Class>();
            printAncestor(c, l);
            if (l.size() != 0) {
                for (Class<?> cl : l)
                    System.out.format("  %s%n", cl.getCanonicalName());
                System.out.format("%n");
            } else {
                System.out.format("  -- No Super Classes --%n%n");
            }

            System.out.format("注解 Annotations:%n"); // 注解
            Annotation[] ann = c.getAnnotations();
            if (ann.length != 0) {
                for (Annotation a : ann)
                    System.out.format("  %s%n", a.toString());
                System.out.format("%n");
            } else {
                System.out.format("  -- No Annotations --%n%n");
            }

        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }

    private static void printAncestor(Class<?> c, List<Class> l) {
        Class<?> ancestor = c.getSuperclass();
        if (ancestor != null) {
            l.add(ancestor);
            printAncestor(ancestor, l);
        }
    }
}

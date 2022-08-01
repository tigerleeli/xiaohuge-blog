package lesson12.lesson1201;

public class Demo {
    public static void main(String[] args) {
        Class<?> c = Student.class;
        ClassPreamble annotation = c.getAnnotation(ClassPreamble.class);
        System.out.println(annotation.name());
        System.out.println(annotation.author());
        System.out.println(annotation.date());
        System.out.println(annotation.currentRevision());
    }
}

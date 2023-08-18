package lesson14.lesson1003;

public class Demo {
    public static void print(Box<? extends Number> box) {
        System.out.println(box.get());
    }

    public static void main(String[] args) {
        Box<Integer> box = new Box<>();
        box.set(100);
        Demo.print(box);
    }
}

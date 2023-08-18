package lesson14.lesson1402;

import java.util.Collection;

public class Box {
    public <T> void test() {
    }

    public <T> T set(T t) {
        return t;
    }

    public <T> T test(T t) {
        return t;
    }

    public static void main(String[] args) {
        Box box = new Box();
        String s = box.set("小明");
        int i = box.set(100);
    }
}

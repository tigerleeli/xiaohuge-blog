package lesson15.lesson1502;

public class Demo2 {
    public static void test() throws Exception {
        int i = 10 / 0;
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

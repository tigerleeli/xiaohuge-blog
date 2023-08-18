package lesson15.lesson1501;

public class Demo {
    public static void main(String[] args) {
        try {
            // 代码
        } catch (Exception e) {
            // 处理异常
        } finally {
            //
        }

//        test();

        test2();
    }

    public static void test1() {
        try {
            int i = 10 / 0;
            System.out.println("无法继续执行了");
        } catch (ArithmeticException e) {
            System.out.println("出现表达式异常");
        } catch (Exception e) {
            System.out.println("出现通用异常");
        } finally {
            System.out.println("不管怎样都要执行");
        }

        throw new RuntimeException();
    }

    public static void test2() {

        try {
            int money = -1;
            if (money < 0) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            System.out.println("出现异常啦");
        } finally {
            System.out.println("不管怎样都要执行");
        }

    }
}

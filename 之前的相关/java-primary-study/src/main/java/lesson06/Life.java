package lesson06;

public interface Life {
    // 玩游戏
    void playGame();

    // 默认方法(Java8)
    default void sleep() {
        System.out.println("睡觉");
    }

    // 静态方法(Java8)
    static void print() {
        System.out.println("静态方法");
    }
}

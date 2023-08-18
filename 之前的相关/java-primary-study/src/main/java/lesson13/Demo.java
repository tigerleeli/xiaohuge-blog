package lesson13;

public class Demo {
    public static void main(String[] args) {
        for (Season season : Season.values()) {
            System.out.println(season);
        }
        // 运行结果
        // WINTER
        // SPRING
        // SUMMER
        // FALL
    }
}

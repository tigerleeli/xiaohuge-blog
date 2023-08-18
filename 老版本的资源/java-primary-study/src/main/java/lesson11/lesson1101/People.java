package lesson11.lesson1101;

public class People implements Eat, Sleep {
    @Override
    public void eat() {
        System.out.println("吃饭");
    }

    @Override
    public void sleep(int hour) {
        System.out.println("睡觉，时长：" + hour);
        if (hour <= 0) {
            // 睡眠时长小于0抛出异常
            throw new RuntimeException();
        }
    }
}

package lesson06;

public class Student implements Life, Work {
    @Override
    public void playGame() {
        System.out.println("学生也要玩游戏");
    }

    @Override
    public void makeMoney() {
        System.out.println("学生也要赚钱");
    }
}

package lesson07;

public class Demo {

    public static void doAction(Action action) {
        action.work();
    }

    public static void main(String[] args) {
        Action teacher = new Teacher();
        doAction(teacher);

        Action programmer = new Programmer();
        doAction(programmer);
    }
}

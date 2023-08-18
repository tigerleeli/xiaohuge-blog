package lesson10.lesson1004;

public class Student {
    public String name;
    public int age;
    private float height;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}

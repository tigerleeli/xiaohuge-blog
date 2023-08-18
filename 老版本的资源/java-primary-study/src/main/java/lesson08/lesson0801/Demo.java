package lesson08.lesson0801;

public class Demo {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student("张三");

        /**
         * 运行结果：
         *
         * People()
         * Student()
         *
         * People()
         * Student(张三)
         */
    }
}

package lesson10.lesson1001;

import java.io.Serializable;


@MyAnnotation(value = "学生类")
public class Student<T> extends People implements Serializable {
    private String name;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

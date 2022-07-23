package lesson02;

import lesson01.Student;

import java.math.BigDecimal;
import java.math.BigInteger;

public class RelType {
    public static void main(String[] args) {
        // int型的包装类型
        Integer a = new Integer(10);

        // 自动装箱
        Integer b = 10;

        // 自动拆箱
        int c = new Integer(10);

        BigInteger number = new BigInteger("100");

        int i = 1000;

        String str = "hello world";

        BigDecimal money = new BigDecimal("99.99");

        Student student = new Student();

    }
}

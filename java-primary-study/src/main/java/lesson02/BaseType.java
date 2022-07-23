package lesson02;

public class BaseType {
    public static void main(String[] args) {
        // 短整数 2个字节
        short s = 1;

        // 整数 4个字节
        int i = 1000;

        // 长整数 8个字节
        long l = 100000000L;

        // 单精度浮点数 4个字节
        float f = 1.5f;

        // 双精度浮点数 8个字节
        double d = 100000.5d;

        // 字节 0x表示16进制，0x41 表示大写的A，可以参考ASCII码表
        byte b = 0x41;

        // 字符 2个字节
        char c = 'a';

        // 布尔 没有明确的规定
        boolean bool = false;

    }

}

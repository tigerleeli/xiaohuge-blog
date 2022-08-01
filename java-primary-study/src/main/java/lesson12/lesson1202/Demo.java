package lesson12.lesson1202;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Demo {
    public static void main(String[] args) {
        try {
            // 模拟 spring 容器
            Map<String, Object> map = new ConcurrentHashMap<>();

            // 模拟 扫描所有有MyService注解的类
            Class<?> c = UserServiceImpl.class;
            MyService annotation = c.getAnnotation(MyService.class);
            if (annotation != null) {
                // 模拟 有MyService注解的类生成该类的一个实例对象
                Object obj = c.newInstance();

                // 模拟 保存到容器中 key就是注解的值 userService
                map.put(annotation.value(), obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

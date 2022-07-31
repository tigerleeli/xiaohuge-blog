package lesson11.lesson1101;

import java.lang.reflect.Proxy;

public class Demo {
    public static void main(String[] args) {
        People obj = new People();
//        Object proxyObj = Proxy.newProxyInstance(
//                obj.getClass().getClassLoader(),
//                obj.getClass().getInterfaces(),
//                new ProxyInvocationHandler(obj));

        Object proxyObj = Proxy.newProxyInstance(
                People.class.getClassLoader(),
                new Class[]{Eat.class, Sleep.class},
                new ProxyInvocationHandler(obj));

        Eat eatObj = (Eat) proxyObj;
        eatObj.eat();
        System.out.println();

        Sleep sleepObj = (Sleep) proxyObj;
        sleepObj.sleep(8);
        System.out.println();

        sleepObj.sleep(-1);
        System.out.println();
    }
}

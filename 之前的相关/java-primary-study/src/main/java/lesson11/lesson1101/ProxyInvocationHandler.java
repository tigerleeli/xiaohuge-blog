package lesson11.lesson1101;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 统一处理类
public class ProxyInvocationHandler implements InvocationHandler {
    private Object obj;

    public ProxyInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        Object result;
        try {
            System.out.println("方法调用之前");
            result = m.invoke(obj, args);
        } catch (InvocationTargetException e) {
            System.out.println("统一处理异常");
            return null;
        } finally {
            System.out.println("方法调用之后");
        }
        return result;
    }
}

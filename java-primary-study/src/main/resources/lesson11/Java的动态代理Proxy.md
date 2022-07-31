> 动态代理类是实现在运行时指定的接口列表的类，这样通过类实例上的一个接口的方法调用将被编码并通过统一接口分派到另一个对象。

## 先提出一个问题？

数据库操作需要以下流程： 获取数据库连接->执行sql->提交事务->异常回滚事务->释放连接。

但是我们开发很少自己手动去做获取数据库连接、提交事务、回滚事务、释放连接等这些操作。

#### **为什么我们不需要做这些操作？？？**

那是有像`Spring`这样的框架，它底层用了**动态代理**技术，
我们只需要写业务sql就可以了，其它像获取数据库连接、释放连接、提交事务、回滚事务等操作
交给了**代理类**去完成，
*因为这些操作每个方法都是一样的，如果我们每个方法都去写那不是要疯掉。*


**代理类**在执行**被代理类**方法的前后加上自己的操作。

---

传统数据库操作方式。
```java
// 加载驱动类
Class.forName("com.mysql.jdbc.Driver");
Connection conn;
PreparedStatement ps1;
PreparedStatement ps2;
try {
    // 获取连接
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xxx", "root", "password");
    ps1 = conn.prepareStatement("update user set age = 1");
    ps2 = conn.prepareStatement("update user set money = 2");
    // 执行sql
    ps1.executeUpdate();
    ps2.executeUpdate();
    // 提交事务
    conn.commit();
 } catch (SQLException e) {
    // 回滚事务
    conn.rollback();
 } finally {
    // 关闭连接
    ps1.close();
    ps2.close();
    conn.close();
 }
}
```

## 看代码 动态代理到底怎么实现的

### 代理接口 `Eat`和`Sleep`。
```java
// 接口 吃
public interface Eat {
    // 吃饭
    void eat();
}

// 接口 谁
public interface Sleep {
    // 睡觉
    void sleep(int hour);
}
```

---

### 被代理类 `People`
它实现了两个接口 `Eat`、`Sleep`。
```java
public class People implements Eat, Sleep {
    @Override
    public void eat() {
        System.out.println("吃饭");
    }

    @Override
    public void sleep(int hour) {
        System.out.println("睡觉，时长：" + hour);
        if (hour <= 0) {
            // 睡眠时长小于0抛出异常
            throw new RuntimeException();
        }
    }
}
```
---
### 代理统一方法处理类 `ProxyInvocationHandler`
处理类需要实现`InvocationHandler`接口，获得`invoke`方法。

`invoke`方法有三个参数：代理类、被代理类的方法、被代理类的方法参数。

```java
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
```
--- 

### 生成代理类

通过`Proxy`类的静态方法`newProxyInstance`生成代理类。

需要传递三个参数：类加载器、被代理接口数组、统一处理类。

```java
public class Demo {
    public static void main(String[] args) {
        People obj = new People();
        Object proxyObj = Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
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
```
类加载器可以通过class对象的`getClassLoader()`方法获得。

*还记得获取Class对象的几种方法吗？？？*

这里的`obj.getClass().getInterfaces()`得到结果就是接口的class数组。就等同于

```java
new Class[]{Eat.class, Sleep.class}
```

### 运行结果
```java
方法调用之前
吃饭
方法调用之后

方法调用之前
睡觉，时长：8
方法调用之后

方法调用之前
睡觉，时长：-1
统一处理异常
方法调用之后
```

### 通过代码理解什么是动态代理
生成的**代理类**`proxyObj`可以向上转型为`Eat`接口和`Sleep`接口。
不管调用`Eat`的`eat()`方法还是`Sleep`的`sleep()`
都会统一派发到`InvocationHandler`类的`invoke`方法，
`invoke`方法中在调用**被代理类**的原方法之前和之后都可以进行一些操作，
并且可以对**被代理类**的原方法的异常进行一些处理。




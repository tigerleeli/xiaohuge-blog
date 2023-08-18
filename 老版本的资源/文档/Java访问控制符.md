在Java中，访问控制符用于控制类中的数据成员和方法对其他类的可见性。Java提供了四种访问控制符：public、private、protected、默认（即没有任何修饰符）。

## public

public是一种最常用和最开放的访问控制符，它允许数据和方法可以被类中的任何方法、所有类和所有包中的类访问。

下面是一个基于“学生”类的代码示例：

```java
public class Student {
    // 公共变量
    public String name;
    
    // 公共方法
    public void speak() {
        System.out.println("我的名字是 " + name);
    }
}
```

在上述代码中，我们定义了一个名为“Student”的类，并在该类中定义了一个公共变量“name”和一个公共方法“speak()”。因为这些是公共的，任何类都可以自由地查看或修改变量，也可以调用该方法。

## private

private是最严格的访问控制符，它阻止数据和方法被其他任何类或方法访问。private适用于那些在特定类中使用，并且不希望其他类访问的数据和方法。

```java
public class Student {
    // 私有变量
    private String name;
    
    // 构造方法
    public Student(String name) {
        this.name = name;
    }
    
    // 私有方法
    private void sayHello() {
        System.out.println("你好，我是 " + name);
    }
    
    // 公共方法
    public void introduce() {
        sayHello();
    }
}
```

在上述代码中，我们定义了一个名为“Student”的类，并在该类中定义了一个私有变量“name”和一个私有方法“sayHello()”。私有方法只能被类本身的方法调用，而不能被其他类使用，因此只能通过公共方法“introduce()”调用私有方法。

## protected

protected允许子类访问基类中受保护的数据和方法，并且也允许本包中的所有类访问。

```java
public class Person {
    // 受保护的变量
    protected String name;
}

public class Student extends Person {
    // 构造方法
    public Student(String name) {
        this.name = name; // 子类中可以访问和修改受保护的数据
    }  
}
```

在上述代码中，我们定义了一个名为“Person”的基类，其中包含一个受保护变量“name”。然后，我们定义了一个名为“Student”的子类，它可以访问和修改基类中的受保护数据。因为该变量是受保护的，所以其他包中的类不能访问该变量。

## 默认

如果没有任何访问修饰符，则使用默认访问控制符，也称为“包级访问”。这意味着数据和方法可以被同一包内的任何其他类访问。

下面是一个基于“学生”类的代码示例：

```java
class Student {
    // 默认变量
    String name;
    
    // 构造方法
    public Student(String name) {
        this.name = name;
    }
    
    // 默认方法
    void sayHello() {
        System.out.println("你好，我是 " + name);
    }
}
```

在上述代码中，我们定义了一个名为“Student”的类，没有使用任何访问修饰符。这意味着该类只能被同一个包下的其他类访问。默认变量和默认方法也只能被相同包下的其他类或方法访问。

## 总结

Java中访问控制符用于控制类中的数据成员和方法对其他类的可见性。public允许所有访问；private禁止所有访问；protected允许子类访问和本包中所有类访问；默认访问控制符允许同包中的类访问。

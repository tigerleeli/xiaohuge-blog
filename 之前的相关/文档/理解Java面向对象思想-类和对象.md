*在编程入门上有一半的程序员被绊倒在门槛上而从此放弃跨入，从而少了无数的乐趣------小虎哥*

准备出一套体系化的Java基础方面的文章，你会问 市场上已经有那么多Java基础方面的书为什么还要自己再去写文章了? 个人觉得市场上的书籍对于入门程序员不是很友好，入门不是那么简单，“世上最大的浪费就是经验的浪费”，我如果能把我自己微薄的知识向后续程序员传递分享那也是一种很幸福的事情。

“即使台下的观众只有一个，那我也要华丽的完成演出”。

万法归宗，夯实基础才能在不断变化的Java框架上游刃有余。

废话不多说，先从理解Java面向对象思想和Java的类和对象开始。这一章我觉得是最难的。理论思想永远是最重要的，你能想象莱布尼茨从中国道家的阴阳发明了二进制吗。

废话太多，现在开始。

**1、什么是面向对象编程（Object-Oriented Programming OOP**

小虎哥的定义：找出事物的`属性`和`行为`就是面向对象编程。

**2、什么是类`class`和对象`object`**    
例如：`学生`就是一个类，而`小明`、`小红`就是这个类的具体的两个`对象`。

对`学生`这个事物进行归纳，`属性`有姓名、性别、年龄、学号等，
`行为`有学习、睡觉、玩游戏等。

-`属性`在Java中被叫做`字段`。    
-`行为`在Java中被叫做`方法`，在C/C++也被叫做`函数`。

**3、创建一个学生类`Student`**

Java规定：类名首字母大写，字段名首字母小写，方法名首字母小写。

```java
public class Student {
    // 姓名
    String name;

    // 年龄
    int age;

    public float study(String lesson, float hour) {
        System.out.println("姓名：" + name);
        System.out.println("年龄：" + age);
        System.out.println("学习课程：" + lesson);
        System.out.println("学习时长：" + hour);
        // 方法返回多少分钟
        return hour * 60;
    }

    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "小明";
        s1.age = 18;
        float result = s1.study("Java", 1.5f);
        System.out.println("调用study方法返回结果：" + result);
    }
}
```

可以看到代码中有个`main`方法，这个方法是固定格式的，是程序运行的入口方法。程序运行从这开始。

```java
public static void main(String[] args) {
    Student s1 = new Student();
    s1.name = "小明";
    s1.age = 18;
    float result = s1.study("Java", 1.5f);
    System.out.println("调用study方法返回结果：" + result);
}
```
---
**4、类的字段**

字段就是描述事物属性的，可以看到`Student`有两个字段`name`和`age`，类的字段也被称为类的`成员变量`。

```java
// 姓名
String name;

// 年龄
int age;
```
---

**5、类的方法**

何为方法：方法就是 输入->执行(处理)->输出。

这里的`study`方法就是打印学生信息和学习课程和时长信息，并返回学习分钟。

输入：课程`lesson`和小时`hour`。    
执行：打印姓名、年龄、学习课程、学习时长。    
返回：将小时乘以60转化为分钟返回。

```java
public float study(String lesson, float hour) {
    System.out.println("姓名：" + name);
    System.out.println("年龄：" + age);
    System.out.println("学习课程：" + lesson);
    System.out.println("学习时长：" + hour);
    // 方法返回多少分钟
    return hour * 60;
}
```

可以看到方法定义规则如下：

访问限定符 返回值类型  方法名（方法参数）
```java
public float study(String lesson, float hour)
```
**6、如何通过类生成对象**

通过`new`关键字来生成对象，如下。这样就生成了一个具体的学生`s1`。
```java
Student s1 = new Student();
```
**7、如何给对象字段赋值和调用方法**

通过`对象.字段名`就可以给对象的字段赋值，如下。这样就给`s1`这个学生赋值了姓名和年龄。
```java
s1.name = "小明";
s1.age = 18;
```
---
通过`对象.方法(参数)`就可以调用方法，并且可以定义一个变量接受方法返回的值，如下：
```java
float result = s1.study("Java", 1.5f);
```
调用方法就是执行方法，这里传入的参数 课程是`java`和学习时长`1.5f`。
**8、运行结果**

程序运行结果如下：
![04.png](https://note.youdao.com/yws/res/34664/WEBRESOURCE9891a4bb566608bfa93b7938d3d74d10)

关注微信公众号：小虎哥的技术博客

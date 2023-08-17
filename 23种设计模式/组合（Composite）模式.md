**组合（Composite）模式用于将对象组合成树形结构，以表示“整体-部分”关系，使得客户端可以像操作一个独立对象一样访问组合类对象。**

## 什么是组合模式

组合模式是一种结构型设计模式，它允许我们将一组相似的对象看作一个单一的对象，从而将对象组织成树形结构。在组合模式中，我们可以将对象分为两类：一类是叶子节点，一类是容器节点。叶子节点代表基本元素，容器节点包含许多基本元素或其它容器节点，形成一个递归嵌套结构。

## 组合模式的使用场景

组合模式通常可以应用于以下场景：

1. 当需要表示对象的“整体-部分”层次结构时，可以使用组合模式。例如，家庭中的家具，部件组成了家具，而部件又可以继续嵌套。

2. 当需要统一对待所有的对象时，可以使用组合模式。例如，统计公司所有部门的开销，计算总开销等等。

3. 当需要批量处理相似对象时，可以使用组合模式。例如，对一组文件进行操作，它们可能是独立的文件，也可能是文件夹，而文件夹中也可能包含了更多的文件和文件夹。

## 组合模式的代码示例

在 Java 程序中，我们可以通过以下步骤来应用组合模式：

1. 定义 Component 类，它是组合中的所有对象（包括叶子节点和容器节点）的基类或接口，并定义确定组合接口和行为的操作。

2. 创建 Leaf 类，它是组合中的叶子节点，它实现了 Component 接口，并且只拥有自己的操作。

3. 创建 Composite 类，它是组合中的容器节点，它也实现了 Component 接口，但是它拥有一个或多个子节点，并且实现了操作组合相结合的操作。

以下是一个简单的示例代码，用于演示在 Java 程序中如何使用组合模式：

```java
abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public abstract void add(Component component);

    public abstract void remove(Component component);

    public abstract void display();
}

class Leaf extends Component {
    public Leaf(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        System.out.println("叶子节点无法添加子节点.");
    }

    @Override
    public void remove(Component component) {
        System.out.println("叶子节点无法删除子节点.");
    }

    @Override
    public void display() {
        System.out.println(name);
    }
}

class Composite extends Component {
    private List<Component> componentList = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        componentList.add(component);
    }

    @Override
    public void remove(Component component) {
        componentList.remove(component);
    }

    @Override
    public void display() {
        System.out.println(name);
        for (Component component : componentList) {
            component.display();
        }
    }
}

// 客户端调用
public class Client {
    public static void main(String[] args) {
        Composite root = new Composite("根节点");
        Leaf leaf1 = new Leaf("叶子节点1");
        Leaf leaf2 = new Leaf("叶子节点2");
        Composite composite1 = new Composite("容器节点1");
        Composite composite2 = new Composite("容器节点2");

        composite1.add(leaf1);
        composite2.add(leaf2);

        root.add(composite1);
        root.add(composite2);

        root.display();
    }
}
```

在以上示例代码中，我们定义了一个 Component 类，它是组合中的所有对象的基类或接口。通过创建 Leaf 和 Composite 两个类，来实现叶子节点和容器节点，并在具体的组合中添加操作或方法。

在 Client 类中，我们通过创建一个根节点，并添加了一些叶子节点和容器节点。通过调用根节点的 display() 方法，展示了整个组合树形结构。

以下是上面示例代码的运行结果：

```
根节点
容器节点1
叶子节点1
容器节点2
叶子节点2
```

我们可以看到，我们通过组合模式将叶子节点和容器节点看成一个独立的对象，并将它们组织成了一颗树形结构。在客户端调用display()方法后，输出了整个组合树的结构，包括叶子节点和容器节点。

## 组合模式的实际应用
一些常见的框架及其使用组合模式的具体场景：

1. Swing：在 Java GUI 编程中，Swing 组件库使用了组合模式，它将容器组件和子组件都视为组件对象，并将它们组织成树形结构，从而形成一个完整的 GUI 界面。

2. AWT：AWT 是 Java 最初的 GUI 库，也使用了组合模式。它将界面部件（包括面板、按钮、文本框等）视为对象，将它们组织成树形结构，从而形成一个 GUI 界面。

3. Struts2：Struts2 框架中的 Action、ActionContext 和 Interceptor 等都使用了组合模式，将它们组织成一个处理请求、返回响应的处理链。

4. Android：在 Android 中，View 是组合模式的最典型运用，它将不同类型的控件(如Button、TextView、ImageView等)组合成一个视图层次结构，并为所有的控件提供了一个共同的接口。

关注微信公众号：“小虎哥的技术博客”。我们会定期发布关于Java技术的详尽文章，让您能够深入了解该领域的各种技巧和方法，让我们一起成为更优秀的程序员👩‍💻👨‍💻！

**适配器（Adapter）设计模式用于将两个不兼容的接口之间建立联系。**

## 什么是适配器模式

适配器模式是一种结构型设计模式，用于将两个不兼容的接口之间建立联系。适配器模式的核心思想是定义一个适配器类，该类实现原本的接口，并添加一个新接口，用于连接两个不兼容的接口。

## 适配器模式的使用场景

适配器模式通常可以在以下情况下使用：

1. 当需要使用一个已存在的类，但其接口与所需接口不匹配时，可以使用适配器模式。

2. 当需要重用一些处于其他应用程序中的类时，适配器模式可以将这些类与当前应用程序进行适配。

3. 当需要通过多个类实现某个接口，而不想创建一个类层次结构时，可以使用适配器模式。

## 适配器模式的代码示例

在Java程序中，我们可以通过以下步骤来应用适配器模式：

1. 定义需要适配的接口和类。

2. 创建适配器类，该类实现需要适配的接口并添加一个新接口。

3. 在客户端中使用适配器类。


下面我们以一个音乐播放器为例，一起来看一下如何在 Java 程序中应用适配器模式。

```java
// 音乐播放器原本的接口
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// 需要适配的类
class AudioPlayer {
    public void playAudio(String fileName) {
        System.out.println("播放音频: " + fileName);
    }
}

// 创建适配器类
class AudioAdapter implements MediaPlayer {
    private AudioPlayer audioPlayer = new AudioPlayer(); // 组合需要适配的类

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) { // 支持mp3格式
            audioPlayer.playAudio(fileName);
        } else if (audioType.equalsIgnoreCase("mp4") || audioType.equalsIgnoreCase("vlc")) { // 不支持其他格式，需要适配
            // 创建新的适配器逻辑
            System.out.println("播放 " + audioType + " 格式的文件: " + fileName);
        } else {
            System.out.println("不支持该格式的文件. " + audioType + " 格式不被支持.");
        }
    }
}

// 在客户端中使用适配器类
public class MusicPlayer {
    public static void main(String[] args) {
        MediaPlayer player = new AudioAdapter();
        player.play("mp3", "好久不见.mp3");
        player.play("mp4", "梁祝.mp4");
        player.play("vlc", "音乐.vlc");
    }
}
```

在以上代码示例中，AudioPlayer类是需要适配的类，它和 MusicPlayer 类所需的音乐播放器 MediaPlayer 接口不兼容。我们创建了一个适配器类 AudioAdapter，该类实现了 MediaPlayer 接口，同时在内部创建了 AudioPlayer 对象，用于适配 AudioPlayer 和 MediaPlayer 接口不兼容的问题。

在客户端 MusicPlayer 类中，我们通过调用 AudioAdapter 的 play() 方法，在适配 AudioPlayer 和 MediaPlayer 之间建立联系，实现了适配器模式。

以下是上面示例代码的运行效果：

```
播放音频: 好久不见.mp3
播放 mp4 格式的文件: 梁祝.mp4
播放 vlc 格式的文件: 音乐.vlc
```

可以看到，我们通过 AudioAdapter 类适配了 AudioPlayer 和 MediaPlayer 接口，成功地播放了 mp3 格式的音频文件，并且在不支持的 mp4 和 vlc 格式的文件中也适配出了播放音乐的方法，极大地提高了代码的复用性。

## 适配器模式的实际应用

以下是一些常见的框架及其使用适配器模式的具体场景：

1. JDBC：Java 数据库连接（JDBC）是 Java 编程语言中的一种标准 SQL 数据库接口，它提供了一种 Java 编程语言访问关系型数据库的方法。JDBC 的内部实现使用了适配器模式，将不同数据库提供的接口适配成了统一的接口，从而让 Java 应用程序与任何数据库相兼容。

2. Spring MVC：Spring 框架中的 MVC 控制器映射器（HandlerMapping）实现就是使用适配器模式的，将不同的请求 URL 路径映射到不同的处理器处理，并统一返回结果。

3. Android：在 Android 中，ListView 和 GridView 控件的适配器类（Adapter）就是适配器模式的经典应用，将数据模型和视图之间进行适配，从而让 ListView 和 GridView 能够显示多样化的数据。

4. Log4j：Log4j 日志框架使用了适配器模式，它将不同的日志输出 API（如：Java 日志 API，Apache Commons Logging 等）包装起来，并提供一种统一的、易于使用的接口。

关注微信公众号：“小虎哥的技术博客”。我们会定期发布关于Java技术的详尽文章，让您能够深入了解该领域的各种技巧和方法，让我们一起成为更优秀的程序员👩‍💻👨‍💻！

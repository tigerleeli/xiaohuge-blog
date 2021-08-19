https://www.jianshu.com/p/a7deab85b489

**目录 Spring Cloud Alibaba 微服务系列文章**
- [0、Spring Cloud Alibaba微服务系列-准备工作]()
- [1、Spring Cloud Alibaba微服务系列-服务注册中心Nacos]()
- [2、Spring Cloud Alibaba微服务系列-配置中心Nacos]()
- [3、Spring Cloud Alibaba微服务系列-服务调用Open Feign]()
- [4、Spring Cloud Alibaba微服务系列-服务网关Gateway]()
- [5、Spring Cloud Alibaba微服务系列-分布式锁Redisson]()
- [6、Spring Cloud Alibaba微服务系列-分布式事务Seata]()
- [7、Spring Cloud Alibaba微服务系列-消息队列RocketMQ]()
- [8、Spring Cloud Alibaba微服务系列-分布式id(雪花算法)]()
- [9、Spring Cloud Alibaba微服务系列-服务限流、熔断、降级Sentinel]()
- [10、Spring Cloud Alibaba微服务系列-服务链路追踪Skywalking]()
- [11、Spring Cloud Alibaba微服务系列-分库分表ShardingSphere]()
- [12、Spring Cloud Alibaba微服务系列-分布式任务调度xxl-job]()
- [13、Spring Cloud Alibaba微服务系列-分布式文件系统Minio]()

### 一、前言
准备出一个关于Spring Cloud Alibaba 微服务的文章，当然Spring Cloud Alibaba也是基于Spring Cloud的。文章以实战为主，代码尽量简洁，让人一看就懂。文章目录如上。

上述的微服务技术栈个人觉得是目前微服务在国内的主流技术了(阿里体系为主)，我这里只是抛砖引玉，一个好的技术是入门简单，深入很难，让人有无限探索的欲望。

### 二、版本号规定
Spring Boot、Spring Cloud、Spring Cloud Alibaba版本对应关系点击查看[版本对应](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E)，这三者不能乱用，要对应好，官方有说明。

我们用的是当前最新的版本：
- Spring Boot: `2.4.2`
- Spring Cloud: `2020.0.2`
- Spring Cloud Alibaba：`2021.1`

### 三、sql语句
前期备需要创建一个数据库`demo`，创建下面两张表，建表如下：

```sql
CREATE TABLE `product`
(
    `id`     bigint(20)                             NOT NULL DEFAULT '0' COMMENT '主键id',
    `name`   varchar(20) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
    `number` int(11)                                NOT NULL DEFAULT '0' COMMENT '商品数量',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `product_order`
(
    `id`          bigint(20) NOT NULL COMMENT '主键id',
    `product_id`  bigint(20) NOT NULL COMMENT '商品id',
    `number`      int(11)    NOT NULL COMMENT '数量',
    `create_time` datetime   NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
```
- 说明1：`product`商品表，三个字段 `id`:商品id   `name`:商品名称  `number`:商品数量
- 说明2：`product_order` 订单表，四个字段 `id`:订单id     `product_id`:商品id    `number`:订单商品数量  `create_time`:创建时间

后面我们做的最多的就是操作：商品数量减少，同时创建一个订单。（模拟客户下单或者抢购商品的操作：判断库存数量够不够，然后减库存，再创建一个新订单）

### 四、Idea创建多模块项目
这里简单说一下在一个项目下创建多模块    
**4.1 创建一个Maven项目，修改`pom.xml`文件如下，主要就是更改` <packaging>pom</packaging>`**，然后删除不必要的文件，这里`pom.xml`就只是做模块管理。
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.llh</groupId>
    <artifactId>spring-cloud-alibaba-demo</artifactId>
    <version>1.0.0</version>
    <name>spring-cloud-alibaba-demo</name>
    <description>spring cloud alibaba demo</description>
    <packaging>pom</packaging>
</project>
```
**4.2 右击项目，创建模块**


**4.3 创建模块后，在根目录下的`pom.xml`中添加模块名称**


### 五、创建`parent`模块
`parent`模块做所有Jar包依赖的版本号管理，其它所有模块都依赖`parent`模块。    
`parent`模块只有一个`pom.xml`,内容如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.2</version>
        <relativePath/>
    </parent>

    <modelVersion>4.0.0</modelVersion>
    <groupId>com.llh</groupId>
    <artifactId>parent</artifactId>
    <version>1.0.0</version>
    <name>parent</name>
    <description>parent description</description>
    <packaging>pom</packaging>

    <properties>
        <spring-cloud.version>2020.0.2</spring-cloud.version>
        <spring-cloud-alibaba.version>2021.1</spring-cloud-alibaba.version>
        <mybatis.version>2.1.4</mybatis.version>
        <mybatis-plus.version>3.4.2</mybatis-plus.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>${spring-cloud-alibaba.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>${mybatis.version}</version>
            </dependency>
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>${mybatis-plus.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
```
- 说明1：`dependencyManagement`里面就是做版本号管理，后面你在各个模块添加依赖`dependency`的时候不需要写版本号`version`了。版本号的管理很重要，微服务都是一个个模块，如果没有做好版本号管理，后续模块多了会混乱的。
- 说明2：在`<parent>`中可以看到我们这个配置文件也依赖了`spring-boot-starter-parent`，这是Spring Boot 的parent，我们现在也自定义了自己的parent，后续各模块也是同样的用法去引用我们自己定义的parent。

### 六、结语
如果后续发现文章中有什么错误可以直接在评论中打出，这边所有文章和源码保障是原创，而且代码肯定是跑通的，源代码我这边都会同步到GitHub中，希望我们一起进步！

同步微信公众号：小虎哥的技术博客
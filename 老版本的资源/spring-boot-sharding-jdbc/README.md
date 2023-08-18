### 一、前言
系统出现的第一个瓶颈往往是数据库瓶颈。ShardingJDBC是ShardingSphere的一个子项，它提供数据库分库分表、读写分离等解决方案。像是京东、当当、唯品会、转转、中通、三只松鼠等都在用ShardingSphere。

*一句话总结：它是国产的、开源的、配置简单的一套开源的分布式数据库解决方案。他的作者是张亮 [https://github.com/terrymanu](https://github.com/terrymanu)*

官网地址：[https://shardingsphere.apache.org/](https://shardingsphere.apache.org/)

### 二、准备工作
创建`sharding_0`和`sharding_1`两个数据库。两个数据库表字段全部一样，数据库脚本如下：

```sql
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `company_id` bigint(20) NULL DEFAULT NULL COMMENT '公司id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

```
### 三、pom.xml
主要添加`mybatis`、`mybatis-plus`、`mysql`、`sharding-jdbc`，如下：

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
    <artifactId>spring-boot-sharding-jdbc</artifactId>
    <version>1.0.0</version>
    <name>spring-boot-sharding-jdbc</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <mybatis-spring-boot.version>2.1.4</mybatis-spring-boot.version>
        <mybatis-plus.version>3.4.2</mybatis-plus.version>
        <hutool.version>5.7.9</hutool.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>${mybatis-spring-boot.version}</version>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>${mybatis-plus.version}</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <dependency>
            <groupId>org.apache.shardingsphere</groupId>
            <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
            <version>4.1.1</version>
        </dependency>

    </dependencies>

</project>
```
### 四、application.properties
- 这里配置两个数据库节点`ds0`、`ds1`，分别对应两个数据库 `sharding_0`和`sharding_1`
- 公司表`company`根据`id`分库，规则为`ds$->{id % 2}`，也就是偶数在`sharding_0`库，奇数在`sharding_1`库。
- 商品表`product`根据`company_id`默认分库，规则为`ds$->{company_id % 2}`。
- 权限表`permission`为广播表，每个库数据都一样。
```prop
# 应用名称
spring.application.name=sharding-jdbc
# mybatis相关
mybatis.mapper-locations=classpath:mapper/*Mapper.xml
# sharding jdbc
spring.shardingsphere.datasource.names=ds0,ds1
spring.shardingsphere.props.sql.show=true
# ds0
spring.shardingsphere.datasource.ds0.type=com.zaxxer.hikari.HikariDataSource
spring.shardingsphere.datasource.ds0.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.ds0.jdbc-url=jdbc:mysql://localhost:3306/sharding_0?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false
spring.shardingsphere.datasource.ds0.username=root
spring.shardingsphere.datasource.ds0.password=0320
# ds1
spring.shardingsphere.datasource.ds1.type=com.zaxxer.hikari.HikariDataSource
spring.shardingsphere.datasource.ds1.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.ds1.jdbc-url=jdbc:mysql://localhost:3306/sharding_1?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false
spring.shardingsphere.datasource.ds1.username=root
spring.shardingsphere.datasource.ds1.password=0320
# 默认数据库分库规则
spring.shardingsphere.sharding.default-database-strategy.inline.sharding-column=company_id
spring.shardingsphere.sharding.default-database-strategy.inline.algorithm-expression=ds$->{company_id % 2}
# 广播表
spring.shardingsphere.sharding.broadcast-tables=permission
# 自定义分片规则
spring.shardingsphere.sharding.tables.company.actual-data-nodes=ds$->{0..1}.company
spring.shardingsphere.sharding.tables.company.database-strategy.inline.sharding-column=id
spring.shardingsphere.sharding.tables.company.database-strategy.inline.algorithm-expression=ds$->{id % 2}

```
### 五、说明
Sharding-JDBC有逻辑sql和实际sql的概念。如下：

```
Logic SQL: SELECT id,name,create_time,update_time FROM company WHERE id=? 

Actual SQL: ds1 ::: SELECT id,name,create_time,update_time FROM company WHERE id=?  ::: [1]
```    
比如查询id为1的公司，这里查找的数据源就是`ds1`

---

像是多租户的表，比如商品数据是属于某个公司的，就根据公司id分库分表

像是权限表、字典表、系统参数表等，数据量不大，但是访问频繁，就是广播表，每张表的数据都一样。

### 六、结语
相关的接口业务代码这里就不贴了，可以访问github源码查看，自己动手试一下，很简单的。

源码地址：[https://github.com/tigerleeli/xiaohuge-blog/tree/master/spring-boot-sharding-jdbc](https://github.com/tigerleeli/xiaohuge-blog/tree/master/spring-boot-sharding-jdbc)

同步微信公众号：小虎哥的技术博客

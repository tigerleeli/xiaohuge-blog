### 简单记账APP

一款简单的记账软件，包括后端代码和前端代码，后端是基于SpringBoot开发，前端基于uni-app。

整个软件功能很简单，数据库表就4张。麻雀虽小，但五脏俱全，适合在校大学生或者刚入门java后端开发的程序员学习。

前端代码：https://github.com/tigerleeli/money-keep-app

后端代码：https://github.com/tigerleeli/money-keep

再次强调 代码很简单，功能很简单，仅仅是为了刚入坑的小白准备的。
个人觉得如果后续有时间把功能界面完善一下也不比市场上那些收费的记账软件差。

原理都很简单。


### 相关功能
就是记账软件，记录每一笔支出和收入、支出和收入可以分类，比如吃饭、学习、工资等，
然后可以创建账户，记账的时候可以选择这一笔记录在哪个账户下。

- 登录 注册
- 记账
- 分类管理
- 账户管理
- 个人管理
- 统计相关

### 相关技术
后端：
- SpringBoot
- Mybatis Plus
- hutool
- jjwt

前端：
- uni-app 地址：https://uniapp.dcloud.net.cn/
- 需要一点vue知识
- ui用了color ui  地址：https://www.yuque.com/swing-xcgis/hytn5l/qgsd7s


### 数据库语句
用mysql或者mariadb（mysql同一个作者）。建议用mariadb，下载安装简单。mariadb下载地址：https://mariadb.org/


然后创建数据库，名称为***money_keep***，编码为utf8mb4,排序规则utf8mb4_general_ci(其它的也可以)，然后执行如下建表sql。
```sql
-- 创建用户表
CREATE TABLE user
(
    `id`          bigint          NOT NULL AUTO_INCREMENT,
    `username`    varchar(50)  NOT NULL COMMENT '用户名',
    `password`    varchar(200) NOT NULL COMMENT '密码',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    `is_deleted`  int          NOT NULL DEFAULT 0 COMMENT '逻辑删除，0：未删除，1：已删除',
    PRIMARY KEY (`id`)
);

-- 创建账户表
CREATE TABLE account
(
    `id`          bigint            NOT NULL AUTO_INCREMENT,
    `user_id`     bigint            NOT NULL COMMENT '用户ID',
    `name`        varchar(50)    NOT NULL COMMENT '账户名称',
    `balance`     decimal(10, 2) NOT NULL DEFAULT 0 COMMENT '账户余额',
    `create_time` datetime       NOT NULL COMMENT '创建时间',
    `update_time` datetime       NOT NULL COMMENT '更新时间',
    `is_deleted`  int            NOT NULL DEFAULT 0 COMMENT '逻辑删除，0：未删除，1：已删除',
    PRIMARY KEY (`id`),
    KEY           `idx_user_id` (`user_id`) USING BTREE
);

-- 创建分类表
CREATE TABLE category
(
    `id`          bigint         NOT NULL AUTO_INCREMENT,
    `user_id`     bigint         NOT NULL COMMENT '用户ID',
    `name`        varchar(50) NOT NULL COMMENT '分类名称',
    `type`        int         NOT NULL COMMENT '类型，1：支出，2：收入',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    `update_time` datetime    NOT NULL COMMENT '更新时间',
    `is_deleted`  int         NOT NULL DEFAULT 0 COMMENT '逻辑删除，0：未删除，1：已删除',
    PRIMARY KEY (`id`),
    KEY           `idx_user_id` (`user_id`) USING BTREE
);

-- 创建记录表
CREATE TABLE record
(
    `id`          bigint            NOT NULL AUTO_INCREMENT,
    `user_id`     bigint            NOT NULL COMMENT '用户ID',
    `category_id` bigint            NOT NULL COMMENT '分类ID',
    `account_id`  bigint            NOT NULL COMMENT '账户ID',
    `amount`      decimal(10, 2) NOT NULL COMMENT '金额',
    `type`        int            NOT NULL COMMENT '类型，1：支出，2：收入',
    `remark`      varchar(200)   DEFAULT NULL COMMENT '备注',
    `create_time` datetime       NOT NULL COMMENT '创建时间',
    `update_time` datetime       NOT NULL COMMENT '更新时间',
    `is_deleted`  int            NOT NULL DEFAULT 0 COMMENT '逻辑删除，0：未删除，1：已删除',
    PRIMARY KEY (`id`),
    KEY           `idx_user_id` (`user_id`) USING BTREE,
    KEY           `idx_category_id` (`category_id`) USING BTREE,
    KEY           `idx_account_id` (`account_id`) USING BTREE
);
```
### 导入项目
前端代码用 HBuilderX 打开  下载地址：https://dcloud.io/hbuilderx.html

后端代码用 Idea 打开 下载地址：https://www.jetbrains.com/zh-cn/idea/



### 后端相关配置
后端代码修改相关配置：src\main\resources\application.properties

主要是：启动端口，数据库名称、用户名、密码

```properties
spring.application.name=money-keep
server.port=8888
# 数据库配置
spring.datasource.url=jdbc:mysql://localhost:3306/money_keep?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```


### 前端相关配置
路径： \util\constant.js
```js
// #ifdef APP-PLUS
export const remoteUrl = 'http://192.168.1.1![img.png](img.png):8888';
// #endif

// #ifdef H5 || MP-WEIXIN
export const remoteUrl = '/api';
// #endif
```

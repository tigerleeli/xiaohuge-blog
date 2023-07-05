
### DDL
先创建数据库 ***money_keep***，然后执行如下sql。
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
    `remark`      varchar(200)   NOT NULL COMMENT '备注',
    `create_time` datetime       NOT NULL COMMENT '创建时间',
    `update_time` datetime       NOT NULL COMMENT '更新时间',
    `is_deleted`  int            NOT NULL DEFAULT 0 COMMENT '逻辑删除，0：未删除，1：已删除',
    PRIMARY KEY (`id`),
    KEY           `idx_user_id` (`user_id`) USING BTREE,
    KEY           `idx_category_id` (`category_id`) USING BTREE,
    KEY           `idx_account_id` (`account_id`) USING BTREE
);
```

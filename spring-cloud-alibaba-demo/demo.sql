CREATE TABLE `product`
(
    `id`     bigint(20)                             NOT NULL DEFAULT '0' COMMENT '主键id',
    `name`   varchar(20) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
    `number` int(11)                                NOT NULL DEFAULT '0' COMMENT '商品数量',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `order`
(
    `id`          bigint(20) NOT NULL COMMENT '主键id',
    `product_id`  bigint(20) NOT NULL COMMENT '商品id',
    `number`      int(11)    NOT NULL COMMENT '数量',
    `create_time` datetime   NOT NULL COMMENT '创建数量',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


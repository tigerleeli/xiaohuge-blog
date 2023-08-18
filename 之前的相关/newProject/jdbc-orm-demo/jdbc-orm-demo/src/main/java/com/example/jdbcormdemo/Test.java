package com.example.jdbcormdemo;

import java.sql.Connection;

public class Test {

    public static void main(String[] args) {
        try {
            // 获取数据库连接
            Connection connection = ConnectionUtil.getConnection();

            // 创建一个实体管理器
            OrmUtil ormUtil = new OrmUtil(connection);

            // 创建一个User对象
            User user = new User();
            user.setId(1);
            user.setName("张三");
            user.setAge(28);

            // 保存User对象到数据库
            ormUtil.save(user);

            // 修改User对象
            user.setAge(30);
            ormUtil.update(user);

            // 根据id查询User对象
            User foundUser = ormUtil.findById(User.class, 1);
            System.out.println("查询结果："+foundUser); // 输出："张三"

            // 删除User对象
            ormUtil.delete(User.class, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.example.jdbcormdemo;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/jdbc_orm?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false";
        String user = "root";
        String password = "123456";
        return DriverManager.getConnection(url, user, password);
    }
}

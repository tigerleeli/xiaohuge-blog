package lesson11.lesson1101;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcDemo {
    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn;
        PreparedStatement ps1;
        PreparedStatement ps2;
        try {
            // 获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xxx", "root", "password");
            ps1 = conn.prepareStatement("update user set age = 1");
            ps2 = conn.prepareStatement("update user set money = 2");
            // 执行sql
            ps1.executeUpdate();
            ps2.executeUpdate();
            // 提交事务
            conn.commit();
        } catch (SQLException e) {
            // 回滚事务
//            conn.rollback();
        } finally {
//            // 关闭连接
//            ps1.close();
//            ps2.close();
//            conn.close();
        }

    }
}

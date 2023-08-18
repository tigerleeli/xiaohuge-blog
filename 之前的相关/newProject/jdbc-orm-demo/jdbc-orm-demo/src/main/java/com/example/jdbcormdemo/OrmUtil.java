package com.example.jdbcormdemo;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrmUtil {

    private final Connection connection;

    public OrmUtil(Connection connection) {
        this.connection = connection;
    }

    public <T> void save(T entity) throws Exception {
        // 获取实体对象的Class对象
        Class<?> clazz = entity.getClass();
        // 获取实体对象所对应的数据库表名
        String tableName = getTableName(clazz);
        // 存储实体对象的列名和对应的值
        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        // 获取实体对象的字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 设置字段可访问
            field.setAccessible(true);
            // 存储字段名
            columns.add(field.getName());
            // 存储字段值，由于是Object类型，需要强制类型转换
            values.add(field.get(entity));
        }

        // 构建SQL语句
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("INSERT INTO ").append(tableName).append(" (");
        for (int i = 0; i < columns.size(); i++) {
            sqlBuilder.append(columns.get(i));
            if (i < columns.size() - 1) {
                sqlBuilder.append(", ");
            }
        }
        sqlBuilder.append(") VALUES (");
        for (int i = 0; i < values.size(); i++) {
            sqlBuilder.append("?");
            if (i < values.size() - 1) {
                sqlBuilder.append(", ");
            }
        }
        sqlBuilder.append(")");
        String sql = sqlBuilder.toString();

        // 打印SQL语句
        System.out.println("执行新增语句；" + sql);

        // 执行SQL语句
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < values.size(); i++) {
                // 设置参数，由于参数类型不确定，使用setObject方法
                statement.setObject(i + 1, values.get(i));
            }
            statement.executeUpdate();
        }
    }

    public <T> void update(T entity) throws Exception {
        // 获取实体对象的Class对象
        Class<?> clazz = entity.getClass();
        // 获取实体对象所对应的数据库表名
        String tableName = getTableName(clazz);
        // 存储实体对象的非主键列名和对应的值
        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        // 存储主键值
        Object idValue = null;

        // 获取实体对象的字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 设置字段可访问
            field.setAccessible(true);
            // 判断字段是否有@Id注解，若有，则将其值作为主键值存储
            if (field.isAnnotationPresent(Id.class)) {
                idValue = field.get(entity);
            } else {
                // 若没有@Id注解，则将其列名和值存储
                columns.add(field.getName());
                values.add(field.get(entity));
            }
        }

        // 构建SQL语句
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("UPDATE ").append(tableName).append(" SET ");
        for (int i = 0; i < columns.size(); i++) {
            sqlBuilder.append(columns.get(i)).append(" = ?");
            if (i < columns.size() - 1) {
                sqlBuilder.append(", ");
            }
        }
        sqlBuilder.append(" WHERE id = ?");
        String sql = sqlBuilder.toString();

        System.out.println("执行更新语句；" + sql);

        // 执行SQL语句
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < values.size(); i++) {
                // 设置非主键列的参数值
                statement.setObject(i + 1, values.get(i));
            }
            // 设置主键参数值
            statement.setObject(values.size() + 1, idValue);
            statement.executeUpdate();
        }
    }

    public void delete(Class<?> clazz, Object id) throws Exception {
        // 获取实体对象所对应的数据库表名
        String tableName = getTableName(clazz);
        // 构建SQL语句
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        System.out.println("执行删除语句:" + sql);

        // 执行SQL语句
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // 设置参数值
            statement.setObject(1, id);
            // 执行删除操作
            statement.executeUpdate();
        }
    }

    public <T> T findById(Class<T> clazz, Object id) throws Exception {
        // 获取实体对象所对应的数据库表名
        String tableName = getTableName(clazz);
        // 构建SQL语句
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";

        System.out.println("执行查询语句；" + sql);

        // 执行SQL查询
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // 设置参数值
            statement.setObject(1, id);
            // 执行查询操作并获取结果集
            try (ResultSet resultSet = statement.executeQuery()) {
                // 如果有结果，则创建实体对象并设置字段值
                if (resultSet.next()) {
                    T entity = clazz.newInstance();
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        // 根据字段名从结果集中获取对应的值，并设置到实体对象中
                        Object value = resultSet.getObject(field.getName());
                        field.set(entity, value);
                    }
                    return entity;
                }
            }
        }
        return null;
    }

    private String getTableName(Class<?> clazz) {
        // 判断类是否有@Entity注解
        if (clazz.isAnnotationPresent(Entity.class)) {
            // 获取类上的@Entity注解对象
            Entity entityAnnotation = clazz.getAnnotation(Entity.class);
            // 获取注解中的表名
            String tableName = entityAnnotation.name();
            // 如果注解中的表名为空，则将类名转换为小写作为表名
            if (tableName.isEmpty()) {
                tableName = clazz.getSimpleName().toLowerCase();
            }
            return tableName;
        }
        // 如果类没有@Entity注解，抛出异常
        throw new IllegalArgumentException("类没有@Entity注解");
    }
}

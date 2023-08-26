
`config-demo.properties`配置文件
```properties
# 端口
server.port=8021
# 数据库连接信息
spring.datasource.url=jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

`common.properties` 公共配置文件
```properties
# 注册中心相关
spring.cloud.nacos.discovery.server-addr=http://localhost:8848
spring.cloud.nacos.discovery.username=nacos
spring.cloud.nacos.discovery.password=nacos
```

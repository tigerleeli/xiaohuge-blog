**目录 Spring Cloud Alibaba 微服务系列文章**
- [0、Spring Cloud Alibaba微服务系列-准备工作](https://github.com/tigerleeli/xiaohuge-blog)
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

当前的版本对应：
- Spring Boot: `2.4.2`
- Spring Cloud: `2020.0.2`
- Spring Cloud Alibaba：`2021.1`
package com.llh.netty;

import com.llh.netty.server.UdpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(Application.class);

    @Resource
    private UdpServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        logger.info("启动Udp服务...");
        nettyServer.start(10000);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("关闭Udp服务...");
            nettyServer.destroy();
        }));
    }
}

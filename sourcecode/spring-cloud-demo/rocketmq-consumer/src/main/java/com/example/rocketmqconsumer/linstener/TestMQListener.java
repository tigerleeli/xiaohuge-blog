package com.example.rocketmqconsumer.linstener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "consumer-group", topic = "test-topic")
public class TestMQListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("消费者收到：" + s);
    }
}

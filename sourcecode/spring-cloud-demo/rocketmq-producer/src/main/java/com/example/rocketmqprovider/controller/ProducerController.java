package com.example.rocketmqprovider.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("producer")
public class ProducerController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("send/{msg}")
    public String send(@PathVariable String msg) {
        MessageBuilder<String> builder = MessageBuilder.withPayload(msg);
        Message<String> message = builder.build();
        rocketMQTemplate.send("test-topic", message);
        System.out.println("生产者发送：" + msg);
        return msg;
    }

}

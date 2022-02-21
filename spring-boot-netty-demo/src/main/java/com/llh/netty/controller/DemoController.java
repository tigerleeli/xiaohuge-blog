package com.llh.netty.controller;

import com.llh.netty.server.DeviceContext;
import com.llh.netty.server.UdpServerHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.DatagramPacket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
public class DemoController {
    @GetMapping("/send")
    public void send(@RequestParam String deviceId, @RequestParam String msg) {
        DeviceContext deviceContext = UdpServerHandler.deviceContextMap.get(deviceId);
        if (deviceContext != null) {
            try {
                // 发送数据
                deviceContext.getContext().writeAndFlush(new DatagramPacket(Unpooled.wrappedBuffer(msg.getBytes(StandardCharsets.UTF_8)), deviceContext.getAddress())).sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

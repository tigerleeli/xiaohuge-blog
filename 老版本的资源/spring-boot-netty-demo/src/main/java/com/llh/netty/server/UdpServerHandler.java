package com.llh.netty.server;

import com.llh.netty.service.DemoService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.ReferenceCountUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    private static UdpServerHandler handler;

    @Resource
    private DemoService demoService;

    /**
     * 设备上下文对象map
     */
    public static Map<String, DeviceContext> deviceContextMap = new HashMap<>();

    @PostConstruct
    public void init() {
        handler = this;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        // 测试字节 0148454C4C4F574F524C44
        ByteBuf buf = msg.copy().content();
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        // 释放buf
        ReferenceCountUtil.release(buf);
        // 打印
        printHexString(bytes);

        // 保存设备
        String deviceId = String.valueOf(bytes[0]);
        if (deviceContextMap.get(deviceId) != null) {
            deviceContextMap.remove(deviceId);
        }
        deviceContextMap.put(deviceId, new DeviceContext(ctx, msg.sender()));

        // 调用service 保存数据
        handler.demoService.save(new String(bytes));

        // 响应
        byte[] respBytes = "SUCCESS".getBytes(StandardCharsets.UTF_8);
        ctx.writeAndFlush(new DatagramPacket(Unpooled.wrappedBuffer(respBytes), msg.sender())).sync();
    }

    private void printHexString(byte[] data) {
        for (byte datum : data) {
            String hex = Integer.toHexString(datum & 0xff);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase() + " ");
        }
        System.out.println();
    }
}

package com.llh.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.ReferenceCountUtil;

public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        // 测试字节 48454C4C4F 574F524C44
        ByteBuf buf = msg.copy().content();
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        // 释放buf
        ReferenceCountUtil.release(buf);
        // 打印
        printHexString(bytes);

        // 响应
        byte[] respBytes = {(byte) 0x57, (byte) 0x4F, (byte) 0x52, (byte) 0x4C, (byte) 0x44};
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

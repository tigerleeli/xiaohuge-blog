package com.llh.netty.server;

import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;

/**
 * 设备上下文对象
 */
public class DeviceContext {
    private ChannelHandlerContext context;
    private InetSocketAddress address;

    public DeviceContext(ChannelHandlerContext context, InetSocketAddress address) {
        this.context = context;
        this.address = address;
    }

    public ChannelHandlerContext getContext() {
        return context;
    }

    public void setContext(ChannelHandlerContext context) {
        this.context = context;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public void setAddress(InetSocketAddress address) {
        this.address = address;
    }
}

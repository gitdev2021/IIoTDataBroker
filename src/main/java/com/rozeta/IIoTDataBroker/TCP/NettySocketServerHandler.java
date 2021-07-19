package com.rozeta.IIoTDataBroker.TCP;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

@ChannelHandler.Sharable
public class NettySocketServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String readMessage = ((ByteBuf) msg).toString(Charset.forName("UTF8"));
        ctx.write(msg);
        System.out.println("message from received: " + readMessage);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
package com.somecode.example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2017/5/5.
 */
public class ServerHandler extends ChannelHandlerAdapter {

    public void channelRead(ChannelHandlerContext context, Object msg) throws UnsupportedEncodingException {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String body = new String(req, "utf-8");
        System.out.println("server:" + body);

        String res = "hi client!";
        context.writeAndFlush(Unpooled.copiedBuffer(res.getBytes()));
    }

    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable) {
        context.close();
    }
}

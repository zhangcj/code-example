package com.somecode.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Administrator on 2017/5/5.
 */
public class Server {

    public static void run(){
        try {
            EventLoopGroup serverloopGroup = new NioEventLoopGroup();//用于处理服务端接收客户端连接
            EventLoopGroup netLoopGroup = new NioEventLoopGroup();//进行网络通信的（网络读写操作）

            ServerBootstrap bootstrap = new ServerBootstrap();//创建辅助工具类，用于服务器通信的配置
            bootstrap.group(serverloopGroup, netLoopGroup) //绑定通信组
                    .channel(NioServerSocketChannel.class)        // 置顶NIO通信模式
                    .option(ChannelOption.SO_BACKLOG, 1024)        //设置TCP缓冲区
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)      //设置发送缓冲区大小
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)      //设置接受缓冲区大小
                    .option(ChannelOption.SO_KEEPALIVE, true)      //保持连接
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ServerHandler());//具体方法处理
                        }
                    });


            ChannelFuture channelFuture = bootstrap.bind(8765).sync();//进行绑定

            channelFuture.channel().closeFuture().sync();//等待关闭

            serverloopGroup.shutdownGracefully();
            netLoopGroup.shutdownGracefully();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

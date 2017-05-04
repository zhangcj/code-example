package com.somecode.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/5/4.
 */
public class Server implements Runnable {
    //多路复用
    private Selector selector;
    //建立缓冲区
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    //读取缓冲区
    private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

    public Server(int port){
        try {
            //打开路复用器
            this.selector = Selector.open();
            ServerSocketChannel channel = ServerSocketChannel.open();
            //设置非阻塞模式
            channel.configureBlocking(false);
            //channel.bind(,port);
            //server 通道注册到路复用器上，并监听事件
            channel.register(this.selector, SelectionKey.OP_ACCEPT);

            System.out.println("Server start, port:"+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                this.selector.select();//多复路器开始监听
                //复路器结果集
                Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();

                while (keys.hasNext()){
                    SelectionKey key = keys.next();
                    keys.remove();
                    //有效
                    if(key.isValid()){
                        if(key.isAcceptable()){
                            this.accept(key);
                        }
                        if(key.isReadable()){
                            this.read(key);
                        }
                        if(key.isWritable()){
                            this.write(key);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void accept(SelectionKey key) {
        try {
            //获取服务通道连接，执行阻塞方法，注册到复路器上
            ServerSocketChannel channel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = channel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(this.selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key){
        try {
            this.readBuffer.clear();//清空缓冲区数据
            SocketChannel socketChannel = (SocketChannel) key.channel();

            int count = socketChannel.read(this.readBuffer);
            if(count==-1){//没有数据
                key.channel().close();
                key.cancel();
                return;
            }

            //准备读取
            this.readBuffer.flip();
            byte[] bytes = new byte[this.readBuffer.remaining()];
            this.readBuffer.get(bytes);
            String body = new String(bytes).trim();
            System.out.println("server:"+body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(SelectionKey key){

    }
}

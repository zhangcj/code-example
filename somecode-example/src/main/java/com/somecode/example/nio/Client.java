package com.somecode.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Administrator on 2017/5/4.
 */
public class Client {

    public static void run() {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8765);
        SocketChannel channel = null;

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try {
            channel = SocketChannel.open();
            channel.connect(address);

            while (true) {
                byte[] bytes = new byte[1024];
                System.in.read(bytes);

                buffer.put(bytes);
                buffer.flip();

                channel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

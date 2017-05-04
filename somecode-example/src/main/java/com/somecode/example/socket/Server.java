package com.somecode.example.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/5/4.
 */
public class Server {

    final static int PORT = 8976;

    public static void run(){
        ServerSocket server = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            server = new ServerSocket(PORT);
            System.out.println("server start");
            Socket socket = null;

            HandlerExecutorPool executorPool = new HandlerExecutorPool(50,1000);
            while (true){
                socket = server.accept();
                executorPool.execute(new ServerHandler(socket));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(out!=null){
                try{
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

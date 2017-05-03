package com.somecode.example;

import com.somecode.example.future.FutureClient;
import com.somecode.example.future.Task;
import com.somecode.example.masterworkder.Master;
import com.somecode.example.masterworkder.Worker;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
//        FutureClient client = new FutureClient();
//        Task task = client.request("aaa");
//        System.out.println("请求发送成功...");
//        System.out.println("主线程执行其他逻辑...");
//        Thread.sleep(4000);
//        System.out.println("主线程其他逻辑执行完毕");
//
//        String result = task.getResult();
//        System.out.println("获取执行结果："+result);


        Master master = new Master(new Worker(),20);
        for (int i=1;i<=100;i++){
            com.somecode.example.masterworkder.Task task = new com.somecode.example.masterworkder.Task();
            task.setId(i);
            task.setPrice(i*100);

            master.submit(task);
        }

        master.execute();

        long start = System.currentTimeMillis();
        while (true){
            if(master.isComplete()){
                long end = System.currentTimeMillis() - start;

                float priceResult = master.getResult();
                System.out.println("执行结果："+priceResult+"，执行时间："+end);
                break;
            }
        }
    }
}

package com.somecode.example;

import com.somecode.example.consumerprovider.Consumer;
import com.somecode.example.consumerprovider.Data;
import com.somecode.example.consumerprovider.Provider;
import com.somecode.example.future.FutureClient;
import com.somecode.example.future.Task;
import com.somecode.example.masterworkder.Master;
import com.somecode.example.masterworkder.Worker;

import java.util.Random;
import java.util.concurrent.*;

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


//        Master master = new Master(new Worker(),20);
//        for (int i=1;i<=100;i++){
//            com.somecode.example.masterworkder.Task task = new com.somecode.example.masterworkder.Task();
//            task.setId(i);
//            task.setPrice(i*100);
//
//            master.submit(task);
//        }
//
//        master.execute();
//
//        long start = System.currentTimeMillis();
//        while (true){
//            if(master.isComplete()){
//                long end = System.currentTimeMillis() - start;
//
//                float priceResult = master.getResult();
//                System.out.println("执行结果："+priceResult+"，执行时间："+end);
//                break;
//            }
//        }


        BlockingQueue<Data> queue = new LinkedBlockingQueue<Data>(10);

        //生产者
        Provider p1 = new Provider(queue);
        Provider p2 = new Provider(queue);
        Provider p3 = new Provider(queue);

        //消费者
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);

        //缓存线程池，可以创建无穷大的线程
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(p1);
        pool.execute(p2);
        pool.execute(p3);
        pool.execute(c1);
        pool.execute(c2);
        pool.execute(c3);

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        p1.stop();
        p2.stop();
        p3.stop();

        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

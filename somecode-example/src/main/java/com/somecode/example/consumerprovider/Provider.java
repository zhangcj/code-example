package com.somecode.example.consumerprovider;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/5/3.
 */
public class Provider implements Runnable {
    private BlockingQueue<Data> queue = null;
    //多线程是否启动
    private volatile  boolean isRunning = true;
    //id生成器
    private static AtomicInteger count = new AtomicInteger();
    //随机对象
    private static Random r = new Random();

    public Provider(BlockingQueue<Data> queue){
        this.queue = queue;
    }

    public void run() {
        while (isRunning){
            try {
                //随机耗时
                Thread.sleep(r.nextInt(1000));
                //获取数据累计
                int id = count.incrementAndGet();

                Data data = new Data(id,"数据"+id);
                System.out.println("当前线程："+Thread.currentThread().getName()+"，获取数据，id为："+id+", 装载进入缓冲区...");

                if(!this.queue.offer(data,2, TimeUnit.SECONDS)){
                    System.out.println("提交缓存数据失败");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.isRunning = false;
    }
}

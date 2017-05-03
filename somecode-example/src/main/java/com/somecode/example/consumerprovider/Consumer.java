package com.somecode.example.consumerprovider;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2017/5/3.
 */
public class Consumer implements Runnable {
    private BlockingQueue<Data> queue = null;

    public Consumer(BlockingQueue<Data> queue){
        this.queue = queue;
    }

    private static Random r = new Random();

    public void run() {
        while (true) {
            try {
                Data data = this.queue.take();
                //随机耗时
                Thread.sleep(r.nextInt(1000));
                System.out.println("当前消费线程：" + Thread.currentThread().getName() + "，消费成功，消费数据id为：" + data.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

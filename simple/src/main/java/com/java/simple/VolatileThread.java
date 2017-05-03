package com.java.simple;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/5/2.
 */
public class VolatileThread extends Thread {
    //private static volatile int count;
    private static AtomicInteger count = new AtomicInteger(0);
    public void run(){
        addCount();
    }

    private static void addCount(){
        for (int i=0;i<1000;i++){
            //count++;
            count.incrementAndGet();
        }
        System.out.println(count);
    }
}

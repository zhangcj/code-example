package com.java.simple;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/5/2.
 */
public class Queue {

    private final LinkedList<Object> queue = new LinkedList<Object>();
    private AtomicInteger count = new AtomicInteger(0);
    private final int minSize = 0;
    private final int maxSize;

    public Queue(int size){
        this.maxSize = size;
    }
    private final Object lock = new Object();

    public void put(Object obj){
        synchronized (lock){
            while (this.maxSize==count.get()){
                try {
                    lock.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            queue.add(obj);
            count.incrementAndGet();
            System.out.println("新加入元素为："+obj);

            lock.notify();
        }
    }

    public Object take(){
        Object ret = null;

        synchronized (lock){
            while (count.get()==this.minSize){
                try{
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ret = queue.removeFirst();
            count.decrementAndGet();

            lock.notify();
        }

        return ret;
    }

    public int size(){
        return this.count.get();
    }
}

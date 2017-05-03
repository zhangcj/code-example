package com.somecode.example.masterworkder;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Administrator on 2017/5/3.
 */
public class Worker implements Runnable {
    private ConcurrentLinkedQueue<Task> workQueue;
    private ConcurrentHashMap<String,Object> resultMap;

    public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue){
        this.workQueue = workQueue;
    }

    public void setResultMap(ConcurrentHashMap<String,Object> resultMap){
        this.resultMap = resultMap;
    }

    public void run() {
        while (true){
            Task input = this.workQueue.poll();
            if(input == null) break;
            Object output = this.handle(input);
            this.resultMap.put(Integer.toString(input.getId()),output);
        }
    }

    private Object handle(Task input){
        Object output = null;
        try {
            //模拟处理任务
            Thread.sleep(500);
            System.out.println("....线程执行中："+input.getId()+"....");
            output = input.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }
}

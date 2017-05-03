package com.somecode.example.masterworkder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Administrator on 2017/5/3.
 */
public class Master {

    //任务存放容器
    private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<Task>();
    //worker容器
    private HashMap<String,Thread> workers = new HashMap<String, Thread>();
    //结果集
    private ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<String, Object>();

    public Master(Worker worker,int workerCount){
        worker.setWorkQueue(this.workQueue);
        worker.setResultMap(this.resultMap);

        for (int i=0;i<workerCount;i++){
            this.workers.put(Integer.toString(i),new Thread(worker));
        }
    }

    //提交任务
    public void submit(Task task){
        this.workQueue.add(task);
    }

    //执行方法，启动所有worker方法
    public void execute(){
        for (Map.Entry<String,Thread> me:workers.entrySet()){
            me.getValue().start();
        }
    }

    //判断线程是否执行结束
    public boolean isComplete(){
        for (Map.Entry<String,Thread> me:workers.entrySet()){
            if(me.getValue().getState()!=Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    //得到执行结果
    public float getResult(){
        int priceResult = 0;
        for (Map.Entry<String,Object> me:resultMap.entrySet()){
            priceResult += (Integer) me.getValue();
        }

        return priceResult;
    }
}

package com.somecode.example.future;

/**
 * Created by Administrator on 2017/5/3.
 */
public class FutureTask implements Task {

    private SimpleTask simpleTask;
    private boolean isReady = false;

    public synchronized void setSimpleTask(SimpleTask simpleTask){
        if(isReady)
            return;

        this.simpleTask = simpleTask;
        isReady = true;
        notify();
    }

    public synchronized String getResult() {
        while (!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return this.simpleTask.getResult();
    }
}

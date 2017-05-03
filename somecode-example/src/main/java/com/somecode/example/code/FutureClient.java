package com.somecode.example.code;

/**
 * Created by Administrator on 2017/5/3.
 */
public class FutureClient {

    public Task request(final String param){
        final FutureTask futureTask = new FutureTask();

        new Thread(new Runnable() {
            public void run() {
                SimpleTask simpleTask = new SimpleTask(param);
                futureTask.setSimpleTask(simpleTask);
            }
        }).start();

        return futureTask;
    }
}

package com.somecode.example.future;

/**
 * Created by Administrator on 2017/5/3.
 */
public class SimpleTask implements Task {

    private String result;

    public SimpleTask(String param){
        System.out.println("子线程获取传入参数："+param+"，执行一个耗时操作...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("子线程耗时操作完毕。");
        result = "["+param+"]";
    }


    public String getResult() {
        return result;
    }
}

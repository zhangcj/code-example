package com.java.simple;

/**
 * Created by Administrator on 2017/5/2.
 */
public class DubbleCheckClass {
    private volatile static DubbleCheckClass instance;

    public static DubbleCheckClass getInstance(){
        if(instance==null){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (DubbleCheckClass.class){
                if(instance==null){
                    instance = new DubbleCheckClass();
                }
            }
        }

        return instance;
    }
}

package com.java.simple;

/**
 * Created by Administrator on 2017/5/2.
 */
public class CurrentThreadLocal {
    public static ThreadLocal<String> th = new ThreadLocal<String>();

    public void setTh (String value){
        th.set(value);
    }

    public void getTh(){
        System.out.println(Thread.currentThread().getName()+":"+th.get());
    }
}

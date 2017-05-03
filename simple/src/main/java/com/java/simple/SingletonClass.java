package com.java.simple;

/**
 * Created by Administrator on 2017/5/2.
 *
 * dubble check
 * static inner class 推荐
 */
public class SingletonClass {

    private static class Singleton{
        private static Singleton single = new Singleton();
    }

    public static Singleton getInstance(){
        return Singleton.single;
    }
}

package com.java.simple;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args){
//        VolatileThread[] apps = new VolatileThread[10];
//        for (int i = 0; i < 10; i++) {
//            apps[i] = new VolatileThread();
//        }
//
//        System.out.println("实例数组添加完毕，共10个");
//
//        for (int i = 0; i < 10; i++) {
//            apps[i].start();
//        }



//        final ListThread list = new ListThread();
//        final Object lock = new Object();
//        final CountDownLatch countDownLatch = new CountDownLatch(1);
//
//        Thread t1 = new Thread(new Runnable() {
//            public void run() {
//                try {
//                    //synchronized (lock) {
//                        for (int i = 0; i < 10; i++) {
//                            list.add();
//                            System.out.println((i + 1) + " 当前线程：" + Thread.currentThread().getName() + "添加了一个元素");
//                            Thread.sleep(300);
//
//                            if (list.size() == 5) {
//                                System.out.println("已经发出通知了");
//                                //lock.notify();
//                                countDownLatch.countDown();
//                            }
//                        }
//                    //}
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "t1");
//
//        Thread t2 = new Thread(new Runnable() {
//            public void run() {
////                while (true) {
////                    if (list.size() == 5) {
////                        System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list.size=5停止");
////                        throw new RuntimeException();
////                    }
////                }
//
//                //synchronized (lock) {
//                if (list.size() != 5) {
//                    try {
//                        //lock.wait();
//                        countDownLatch.await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止");
//                throw new RuntimeException();
//                //}
//            }
//        }, "t2");
//
//        t2.start();
//        t1.start();
//        //t2.start();


//        final Queue queue = new Queue(5);
//        queue.put("a");
//        queue.put("b");
//        queue.put("c");
//        queue.put("d");
//        queue.put("e");
//
//        System.out.println("size:"+queue.size());
//
//        Thread t1 = new Thread(new Runnable() {
//            public void run() {
//                queue.put("f");
//                queue.put("g");
//            }
//        },"t1");
//
//        t1.start();
//
//        Thread t2 = new Thread(new Runnable() {
//            public void run() {
//                Object o1 = queue.take();
//                System.out.println("移除元素为:"+o1);
//
//                Object o2 = queue.take();
//                System.out.println("移除元素为:"+o2);
//            }
//        },"t2");
//
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        t2.start();


//        final CurrentThreadLocal ct = new CurrentThreadLocal();
//
//        Thread t1 = new Thread(new Runnable() {
//            public void run() {
//                ct.setTh("aaa");
//                ct.getTh();
//            }
//        },"t1");
//
//        Thread t2 = new Thread(new Runnable() {
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                    ct.getTh();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        },"t2");
//
//        t1.start();
//        t2.start();


        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println(DubbleCheckClass.getInstance().hashCode());
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println(DubbleCheckClass.getInstance().hashCode());
            }
        },"t2");

        Thread t3 = new Thread(new Runnable() {
            public void run() {
                System.out.println(DubbleCheckClass.getInstance().hashCode());
            }
        },"t3");

        t1.start();
        t2.start();
        t3.start();
    }
}

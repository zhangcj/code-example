package com.somecode.example;

import com.somecode.example.code.FutureClient;
import com.somecode.example.code.Task;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        FutureClient client = new FutureClient();
        Task task = client.request("aaa");
        System.out.println("请求发送成功...");
        System.out.println("主线程执行其他逻辑...");
        Thread.sleep(4000);
        System.out.println("主线程其他逻辑执行完毕");

        String result = task.getResult();
        System.out.println("获取执行结果："+result);
    }
}

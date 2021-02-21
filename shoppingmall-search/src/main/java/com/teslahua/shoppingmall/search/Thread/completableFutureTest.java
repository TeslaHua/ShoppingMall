package com.teslahua.shoppingmall.search.Thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Zhonghua Wang
 * @Description:
 * @Date:Create：in 2021/2/20 18:59
 * @Modified By：
 */
public class completableFutureTest {

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main...start....");
        /*
        CompletableFuture<Void> future = CompletableFuture.runAsync(()->{
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10/2;
            System.out.println("运行结果：" + i);
        },executorService);
         */
        CompletableFuture<Object> future = CompletableFuture.supplyAsync(()->{
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10/2;
            System.out.println("运行结果：" + i);
            return i;
        });

        System.out.println("main...stop...."+future.get());
    }
}

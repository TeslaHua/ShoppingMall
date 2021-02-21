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

        CompletableFuture<Object> future = CompletableFuture.supplyAsync(()->{
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10/2;
            System.out.println("运行结果：" + i);
            return i;
        },executorService);
        System.out.println("main...stop...."+future.get());

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 0;
            System.out.println("运行结果：" + i);
            return i;
        },executorService).whenComplete((result,exception)->{
            //虽然能得到异常信息，但是没办法修改返回数据。
            System.out.println("异步任务成功完成...结果是："+ result +"异常是：" + exception);
        }).exceptionally(throwable -> {
            //返回一个默认值
            return 10;
        });
        System.out.println("main...stop...."+future.get());
         */

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("运行结果：" + i);
            return i;
        },executorService).handle((result,exception)->{
            if(result != null){
                return result*2;
            }
            if(exception != null){
                return -1;
            }
            return 0;
        });
        System.out.println("main...stop...."+future.get());
    }
}

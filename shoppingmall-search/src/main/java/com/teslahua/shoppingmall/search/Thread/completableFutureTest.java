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

        //whenComplete 感知结果 + exceptionally 处理异常
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

        //handle 函数进行线程后处理
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

        //线程串行化
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(()->{
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("运行结果：" + i);
            return i;
        },executorService).thenAcceptAsync(res->{
            System.out.println("任务2启动："+res);
        },executorService);

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(()->{
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("运行结果：" + i);
            return i;
        },executorService).thenRunAsync(()->{
            System.out.println("任务2启动");
        },executorService);

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("运行结果：" + i);
            return i;
        }, executorService).thenApplyAsync(res -> {
            System.out.println("任务2启动：" + res);
            return 5;
        }, executorService);

        System.out.println("main...start...."+future.get());

        //两个任务组合-都要完成
        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("线程1启动：" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("线程1结束");
            return i;
        }, executorService);

        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("线程2启动：" + Thread.currentThread().getId());
            System.out.println("线程2结束");
            return "hello";
        }, executorService);

        future01.runAfterBothAsync(future02,()->{
            System.out.println("任务3开始：" + Thread.currentThread().getId());
        },executorService);

        CompletableFuture<String> future03 = future01.thenCombineAsync(future02, (result01, result02) -> {
            System.out.println("任务3开始：" + Thread.currentThread().getId());
            System.out.println(result01 + " " + result02);
            return "world!";
        }, executorService);
        System.out.println("任务3结束："+future03.get());

        future01.thenAcceptBothAsync(future02, (result01, result02) -> {
            System.out.println("任务3开始：" + Thread.currentThread().getId());
            System.out.println(result01 + " " + result02);
        }, executorService);

         */

    }
}

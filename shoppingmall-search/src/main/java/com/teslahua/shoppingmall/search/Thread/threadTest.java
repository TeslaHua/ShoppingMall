package com.teslahua.shoppingmall.search.Thread;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * @Author: Zhonghua Wang
 * @Description:
 * @Date:Create：in 2021/2/20 16:44
 * @Modified By：
 */

public class threadTest {

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);  //创建一个固定数量的线程池
    @SneakyThrows
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 1)、继承Thread
         * 2)、实现 Runnable 接口
         * 3)、实现 Callable 接口 + FutureTask (可以拿到返回结果，可以处理异常)
         * 4)、线程池
         *
         * 区别：
         * 1、2 不能得到返回值；3可以得到返回值
         * 1、2、3 都不能实现资源控制
         * 4 可以控制资源，性能稳定
         */

        System.out.println("main 方法开始了 ...");

        /*
        //1、继承Thread 类
        Thread01 thread01 = new Thread01();
        thread01.start();  //启动线程


        //2、实现 Runnable 接口
        Thread thread02 = new Thread(new Runnable01());
        thread02.start();


        //3、实现 Callable 接口 + FutureTask
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable01());  //FutureTask 底层就是Runnable
        new Thread(futureTask).start();

        //阻塞等待整个线程执行完成，获取返回结果
        Integer integer = futureTask.get();
        System.out.println("main 方法结束了 ..." + integer);
        */

        //4、线程池：给线程池直接提交任务。业务代码里面都用线程池，应该将所有的多线程异步任务都交给线程池处理，整个系统一两个线程池
        executorService.execute(new Runnable01());
        System.out.println("main 方法结束了 ...");

    }

    public static class Thread01 extends Thread{

        @Override
        public void run() {
            System.out.println("当前线程："+ Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果："+i);
        }
    }

    public static class Runnable01 implements Runnable{
        @Override
        public void run() {
            System.out.println("当前线程："+ Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果："+i);
        }
    }

    public static class Callable01 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("当前线程："+ Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果："+i);
            return i;
        }
    }
}

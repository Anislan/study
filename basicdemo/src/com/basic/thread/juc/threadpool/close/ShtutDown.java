package com.basic.thread.juc.threadpool.close;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *  关闭线程池
 */
public class ShtutDown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShutDownTask());
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Runnable> list=executorService.shutdownNow();


//       boolean b= executorService.awaitTermination(10L,TimeUnit.SECONDS);
//        System.out.println(b);
//        System.out.println(executorService.isShutdown());
        // 判断线程池是否终止
//        executorService.shutdown();
//        System.out.println(executorService.isShutdown());
//        Thread.sleep(10000);
        // 判断线程池是否完全终止
//        System.out.println(executorService.isTerminated());
        // 新增任务被拒绝了
//        executorService.execute(new ShutDownTask());
    }
}

class ShutDownTask implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断了");
        }

    }
}
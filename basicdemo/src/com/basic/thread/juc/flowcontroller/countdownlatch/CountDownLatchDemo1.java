package com.basic.thread.juc.flowcontroller.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  工厂中，质检，5个工人检查，所有人认为通过，才通过
 */
public class CountDownLatchDemo1 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final  int no = i+1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("NO." + no + "完成了检查。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                    ;
                }
            };
            executorService.submit(runnable);
        }
        System.out.println("等待5个人检查完....");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有人完成了工作，进入下一个环节...");
        executorService.shutdown();
    }

}

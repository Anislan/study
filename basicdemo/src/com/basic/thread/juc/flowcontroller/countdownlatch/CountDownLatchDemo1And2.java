package com.basic.thread.juc.flowcontroller.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  模拟100米跑步，5名选手都准备好了，只等裁判员（起始）一声令下，所有人同时开始跑步
 *  终点裁判员等待所有运行员结束
 */
public class CountDownLatchDemo1And2 {

    public static void main(String[] args) {
        // 起跑点裁员
        CountDownLatch begin = new CountDownLatch(1);
        // 终点裁判员
        CountDownLatch end = new CountDownLatch(5);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("No." + no + "准备完毕，等待发令枪");
                    try {
                        begin.await();
                        System.out.println("No." + no +"开始跑步了");
                        Thread.sleep((no* 700));
                        System.out.println("No."+no+"到达终点！");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        end.countDown();
                    }
                }
            };
            executorService.submit(runnable);
        }
        // 裁判员检查发令枪...
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发令枪响，比赛开始!");
        begin.countDown();
        // 终点裁判等待5个运动员
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有人到达终点，比赛结束");

        executorService.shutdown();
    }
}

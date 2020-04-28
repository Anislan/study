package com.basic.thread.juc.threadpool.simple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  newFixedThreadPool:固定线程池大小
 */
public class FixedThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService=Executors.newFixedThreadPool(4);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Task());
        }
    }
}

class Task implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task ... 正在执行");
        System.out.println(Thread.currentThread().getName());
    }
}

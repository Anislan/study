package com.basic.thread.juc.flowcontroller.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *  多个线程等待许可证，然后去排队现象
 */
public class SemaphoreDemo1 {

    static Semaphore semaphorem = new Semaphore(3,true);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Task());
        }
        executorService.shutdown();
    }

    static class Task implements Runnable{
        @Override
        public void run() {
            try {
                // 一次性拿3个许可证
                semaphorem.acquire(3);
//                semaphorem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"获取到了许可证");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"释放了许可证");
//            semaphorem.release();
            // 一次性释放3个许可证
            semaphorem.release(3);
        }
    }
}


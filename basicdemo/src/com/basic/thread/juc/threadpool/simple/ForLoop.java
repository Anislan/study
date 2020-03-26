package com.basic.thread.juc.threadpool.simple;

import java.util.concurrent.Executors;

/**
 *  for循环创建多个线程
 */
public class ForLoop {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }
//        Executors.newFixedThreadPool();
    }


    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"执行了任务");
        }
    }
}

package com.basic.thread.juc.threadpool.simple;

/**
 *  一个线程
 */
public class EveryTaskOneThread {

    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        thread.start();

    }

        public static class Task implements Runnable{

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"执行了任务");
            }
        }
}

package com.basic.thread.juc.threadpool.simple;

/**
 *  一个线程能反复执行不同的任务方法嘛？
 *
 */
public class OneThreadExecutorTasks {


    public static void main(String[] args) {
        // 任务1
        Runnable task = new Task();

        // 任务2
        Runnable subThread = new SubThread();

        Thread thread = new Thread(task);

        thread.start();
    }

}



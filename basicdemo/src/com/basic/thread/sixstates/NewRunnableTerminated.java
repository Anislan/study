package com.basic.thread.sixstates;

/**
 * 展示线程的NEW、Runnable、Terminated
 * 即使是正在运行，也是Runnable状态，而不是Running
 */
public class NewRunnableTerminated implements  Runnable{


    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        // 打印出New状态
        System.out.println("线程状态:"+thread.getState());
        thread.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印出Runnable的状态，即使是正在运行，而不是Running
        System.out.println("线程状态:"+thread.getState());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印出Runnable的状态，即使是Terminated
        System.out.println("线程状态:"+thread.getState());
    }

    @Override
    public void run() {
        for (int i = 0; i <1000 ; i++) {
            System.out.println(i);
        }
    }
}

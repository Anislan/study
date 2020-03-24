package com.basic.thread.threadobjectmethods;

/**
 *  join期间遇到中断效果
 */
public class JoinInterrupt {
    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();

       Thread thread1= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mainThread.interrupt();
                    Thread.sleep(5000);
                    System.out.println("Thread1 finished");
                } catch (InterruptedException e) {
                    System.out.println("子线程中断");
                }
            }
        });

        thread1.start();
        System.out.println("等待子线程运行完毕");
        try {
            // 一旦join期间被打断，那么在处理这个中断过程中，把子线程所有操作都中止
            // 否则出现不一致情况
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"主线程被中断了");
            // 把中断传递给子线程
            thread1.interrupt();
        }
        System.out.println("子线程已经运行完毕");
    }
}

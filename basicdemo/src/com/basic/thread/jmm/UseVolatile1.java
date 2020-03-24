package com.basic.thread.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  volatile适用的情况1
 */
public class UseVolatile1 implements  Runnable{

    volatile  boolean done = false;

    // 原子包
    AtomicInteger realA = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
           setDone();
            realA.incrementAndGet();
        }
    }

    private void setDone() {
        done = true;
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new UseVolatile1();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("done="+((UseVolatile1)runnable).done);
        System.out.println(((UseVolatile1)runnable).realA.get());
    }
}

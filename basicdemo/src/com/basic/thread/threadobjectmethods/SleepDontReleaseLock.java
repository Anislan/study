package com.basic.thread.threadobjectmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  sleep不释放lock（需要手动释放）
 */
public class SleepDontReleaseLock implements  Runnable{

    private static final Lock LOCK = new ReentrantLock();

    @Override
    public void run() {
        LOCK.lock();
        System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁");
        try {
            Thread.sleep(5000);
            System.out.println("线程"+Thread.currentThread().getName()+"已经唤醒了.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            LOCK.unlock();
        }
    }

    public static void main(String[] args) {
        SleepDontReleaseLock sleepDontReleaseLock = new SleepDontReleaseLock();
        new Thread(sleepDontReleaseLock).start();
        new Thread(sleepDontReleaseLock).start();

    }
}

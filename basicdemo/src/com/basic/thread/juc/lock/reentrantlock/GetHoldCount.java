package com.basic.thread.juc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 *  可重入
 */
public class GetHoldCount {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println(reentrantLock.getHoldCount());
        reentrantLock.lock();
        System.out.println(reentrantLock.getHoldCount());
        // 可重入
        reentrantLock.lock();
        System.out.println(reentrantLock.getHoldCount());
        // 可重入
        reentrantLock.lock();
        System.out.println(reentrantLock.getHoldCount());
        reentrantLock.unlock();
        System.out.println(reentrantLock.getHoldCount());
        reentrantLock.unlock();
        System.out.println(reentrantLock.getHoldCount());
        reentrantLock.unlock();
        System.out.println(reentrantLock.getHoldCount());

    }
}

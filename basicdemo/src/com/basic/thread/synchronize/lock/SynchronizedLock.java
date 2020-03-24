package com.basic.thread.synchronize.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  Synchronized和Lcok对象作类比（等价）
 */
public class SynchronizedLock {

    Lock lock = new ReentrantLock();

    public  synchronized void sync1(){
        System.out.println("我是Synchronized形式的锁");
    }

    public void  sync2(){
        lock.lock();
        try {
            System.out.println("我是lock形式的锁");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SynchronizedLock synchronizedLock = new SynchronizedLock();
        synchronizedLock.sync1();
        synchronizedLock.sync2();
    }
}

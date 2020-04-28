package com.basic.thread.juc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 *  可重入锁
 */
public class RecursionDemo {

    private static ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) {
        accessResource();
    }

    private static  void accessResource(){
        lock.lock();
        try {
            System.out.println("已经对资源进行了处理");
            if(lock.getHoldCount() < 5){
                System.out.println(lock.getHoldCount());
                accessResource();
                System.out.println(lock.getHoldCount());
            }
        }finally {
            lock.unlock();
        }

    }
}

package com.basic.thread.juc.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  Lock不会像synchronized一样，异常的时候不会释放锁
 *  所以最佳实践，finally中释放锁，已保证发生异常的时候锁一定释放
 */
public class MustUnlock implements Runnable{

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new MustUnlock();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("finished");
    }

    private  void lock(){
        lock.lock();
        try {
            // 获取本锁保护的资源
            System.out.println(Thread.currentThread().getName()+"开始执行任务");
            throw new RuntimeException();
        }finally {
//            lock.unlock();
            System.out.println(Thread.currentThread().getName()+"结束执行任务");
        }


    }

    private synchronized  void sync(){
        // 获取本锁保护的资源
        System.out.println(Thread.currentThread().getName()+"开始执行任务");
        throw new RuntimeException();


    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
//            lock();
            sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName()+"结束执行任务");
        }
    }
}

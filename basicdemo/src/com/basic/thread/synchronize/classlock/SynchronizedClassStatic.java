package com.basic.thread.synchronize.classlock;

/**
 *  类锁的第一种形式，static形式
 *   两个线程访问的是synchronized的静态方法，且是两个对象实例
 */
public class SynchronizedClassStatic implements  Runnable{
    static SynchronizedClassStatic instance1 = new SynchronizedClassStatic();
    static SynchronizedClassStatic instance2 = new SynchronizedClassStatic();

    @Override
    public void run() {
        sync();
    }

    public static  synchronized  void sync(){
        System.out.println("我持有类锁。我叫" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "已经释类锁。");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
        // 空的死循环,直到线程执行完毕
        while (t1.isAlive() || t2.isAlive()){

        }
        System.out.println("finished");
    }
}

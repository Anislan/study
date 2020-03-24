package com.basic.thread.synchronize.objectlock;

/**
 *  对象锁示例1，代码块形式
 *  this和lock1（lock2）使用场景：
 *   两个线程访问的是两个不同的对象的同步方法，两个对象持有的是不同的锁
 */
public class SynchronizedObjectLock implements  Runnable{


   static  SynchronizedObjectLock instance1= new SynchronizedObjectLock();
    static  SynchronizedObjectLock instance2= new SynchronizedObjectLock();

//    Object lock1 = new Object();
//    Object lock2 = new Object();

    @Override
    public void run() {
        // this方式
//        synchronized (this){
        // lock对象方式
        synchronized (this){
        System.out.println("我持有lock1对象锁。我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"已经释放lock1对象锁。");
        }

//        synchronized (lock2){
//            System.out.println("我持有lock2对象锁。我叫"+Thread.currentThread().getName());
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"已经释放lock2对象锁。");
//        }

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

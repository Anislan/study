package com.basic.thread.synchronize.seven;

/**
 *  6.同时访问静态与非静态（Synchronized修饰的方法）
 *   this 、class对象是不一样的锁，可以同时运行
 */
public class SynchronizedStaticAndNormal implements  Runnable{

    static SynchronizedStaticAndNormal instance= new SynchronizedStaticAndNormal();


    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")){
            sync ();
        }else{
            sync1();
        }
    }

    private  synchronized static void sync() {
        System.out.println("我是被Synchronized和static修饰的方法。我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"运行结束。");
    }
    private synchronized void sync1() {
        System.out.println("我是被Synchronized修饰的方法。我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"运行结束。");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        // 空的死循环,直到线程执行完毕
        while (t1.isAlive() || t2.isAlive()){

        }
        System.out.println("finished");
    }
}

package com.basic.thread.synchronize.seven;

/**
 *  5.同时访问一个类的不同的普通方法（被Synchronized修饰两个方法），
 *  本质后面指定了this对象做为这把锁，同一个实例来讲，这两个方法没办法同时运行，所以会出现串行的情况
 *
 */
public class SynchronizedDifferentMethod implements  Runnable{

    static SynchronizedDifferentMethod instance= new SynchronizedDifferentMethod();


    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")){
            sync ();
        }else{
            sync1();
        }
    }

    private synchronized void sync() {
        System.out.println("我是被Synchronized修饰的方法。我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"运行结束。");
    }
    private synchronized void sync1() {
        System.out.println("我是没被Synchronized修饰的方法。我叫"+Thread.currentThread().getName());
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

package com.basic.thread.synchronize.seven;

/**
 *  同时 访问同步（Synchronized修饰的方法）和非同步方法。结果是同时运行的，同时结束
 *  Synchronize只作用于自己修饰的方法中
 */
public class SynchronizedYesAndNo implements  Runnable{

    static SynchronizedYesAndNo instance= new SynchronizedYesAndNo();


    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")){
            sync ();
        }else{
            unsync();
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
    private void unsync() {
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

package com.basic.thread.synchronize.classlock;

/**
 *  类锁的第二种形式，
 */
public class SynchronizedClassClass implements  Runnable{
    static SynchronizedClassClass instance1 = new SynchronizedClassClass();
    static SynchronizedClassClass instance2 = new SynchronizedClassClass();

    @Override
    public void run() {
        sync();
    }

    public static   void sync(){
        synchronized (SynchronizedClassClass.class){
            System.out.println("我持有类锁。我叫" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "已经释放类锁。");
        }

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

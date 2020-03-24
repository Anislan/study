package com.basic.thread.synchronize.seven;

/**
 *  7.方法抛异常后，会释放锁。不抛异常前和抛出异常后对比
 *  一旦抛出异常，第二个线程会立刻进入同步方法,意味着锁已经释放。
 */
public class SynchronizedException  implements Runnable{

    static SynchronizedException instance= new SynchronizedException();


    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")){
            sync ();
        }else{
            sync1();
        }
    }

    private  synchronized  void sync() {
        System.out.println("我是被Synchronized修饰的方法。我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 抛出异常后，会释放锁。
        throw  new RuntimeException();
//        System.out.println(Thread.currentThread().getName()+"运行结束。");
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

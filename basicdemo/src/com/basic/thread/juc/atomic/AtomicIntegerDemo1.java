package com.basic.thread.juc.atomic;


import java.util.concurrent.atomic.AtomicInteger;

/**
 *  AtomicInteger的基本用法，对比非原子类的线程安全问题，
 *  使用了原子类之后，不需要加锁，也可以保证线程安全
 */
public class AtomicIntegerDemo1 implements Runnable{

    private  static  final  AtomicInteger atomicInteger=new AtomicInteger();

    public void  incrementAtomic(){
        // 获取当前值，并自增
//        atomicInteger.getAndIncrement();
        // 获取当前值，并自减
//        atomicInteger.decrementAndGet();
        // 获取当前值，并加上预期值
        atomicInteger.getAndAdd(-10);
    }

    private static volatile int basicCount = 0;

    private void incrementBasic(){
        basicCount++;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 atomicIntegerDemo1 = new AtomicIntegerDemo1();
        Thread thread1 = new Thread(atomicIntegerDemo1);
        Thread thread2 = new Thread(atomicIntegerDemo1);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("原子类的结果:"+atomicInteger.get());
        System.out.println("普通变量的结果:"+basicCount);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();;
        }
    }
}

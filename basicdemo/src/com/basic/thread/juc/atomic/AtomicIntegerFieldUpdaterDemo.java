package com.basic.thread.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 *  AtomicIntegerFieldUpdater用法
 */
public class AtomicIntegerFieldUpdaterDemo implements Runnable{

    static Candidate tom;
    static Candidate peter;

    public static AtomicIntegerFieldUpdater<Candidate> socreUpdater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class,"score");

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            peter.score ++;
            socreUpdater.getAndIncrement(tom);
        }
    }

    public static class Candidate{
        volatile    int score;
    }

    public static void main(String[] args) throws InterruptedException {
        tom= new Candidate();
        peter = new Candidate();
        Runnable runnable = new AtomicIntegerFieldUpdaterDemo();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("普通的变量:"+peter.score);
        System.out.println("升级后的结果:"+tom.score);

    }
}

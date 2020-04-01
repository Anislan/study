package com.basic.thread.cas;

public class TwoThreadsCompetition implements Runnable {

    private volatile int value;

    /**
     * 等价于一条CPU指令（加synchronized的保证原子性）
     *
     * @param expectedValue
     * @param newValue
     * @return
     */
    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        // 比较
        if (oldValue == expectedValue) {
            // 交换
            value = newValue;
        }
        return oldValue;
    }

    public static void main(String[] args) throws InterruptedException {
        TwoThreadsCompetition twoThreadsCompetitiom = new TwoThreadsCompetition();
        twoThreadsCompetitiom.value = 0;
        Thread t1 = new Thread(twoThreadsCompetitiom,"Thread1");
        Thread t2 = new Thread(twoThreadsCompetitiom,"Thread2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(twoThreadsCompetitiom.value);
    }

    @Override
    public void run() {
        compareAndSwap(0, 1);
    }
}

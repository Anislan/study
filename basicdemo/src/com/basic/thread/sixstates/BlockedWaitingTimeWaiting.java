package com.basic.thread.sixstates;

/**
 * 展示Blocked、Waiting、TimeWaiting
 */
public class BlockedWaitingTimeWaiting implements  Runnable{


    public static void main(String[] args) {
        BlockedWaitingTimeWaiting runnable = new BlockedWaitingTimeWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        System.out.println(thread1.getState());
        // 线程2打印出BLOCKED
        System.out.println(thread2.getState());
        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread1.getState());
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized  void  syn(){
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

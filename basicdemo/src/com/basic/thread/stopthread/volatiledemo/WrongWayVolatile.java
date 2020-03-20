package com.basic.thread.stopthread.volatiledemo;

/**
 * 演示volatile的局限，看似可行中断线程
 */
public class WrongWayVolatile implements Runnable {

    // 对这个变量具有可见性（多个线程中）
    private  volatile  boolean canceled = false;


    @Override
    public void run() {
        int num = 0;
        try {
        while (num <=100000 && !canceled){
            if(num % 100 == 0){
                System.out.println(num+"是100的倍数。");
            }
            num++;

                Thread.sleep(1);
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile wrongWayVolatile = new WrongWayVolatile();
        Thread thread = new Thread(wrongWayVolatile);
        thread.start();
        Thread.sleep(5000);
        wrongWayVolatile.canceled = true;

    }
}

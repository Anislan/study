package com.basic.thread.stopthread;

/**
 * 如果在执行过程中，每次循环都会调用sleep或者wait等方法，那么不需要每次迭代都检查是否已中断
 */
public class RightWayStopWithSleepEveryLoop {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0 ;
            try {
               //这个检测已经没啥效果了，主要由sleep睡眠时就能监听中断信号了
//            while (num<= 10000 & !Thread.currentThread().isInterrupted()){
           while (num<= 10000 ){
                if(num % 100 == 0){
                    System.out.println(num+"是100的倍数");
                }
                num++;
                // 线程睡眠时，收到中断信号，线程会响应中断，抛出异常
                Thread.sleep(10);
            }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread=new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}

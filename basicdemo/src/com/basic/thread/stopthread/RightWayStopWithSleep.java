package com.basic.thread.stopthread;

/**
 * 带有sleep的中断线程的写法
 */
public class RightWayStopWithSleep {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 300 & !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                }

                // 线程睡眠时，收到中断信号，线程会响应中断，抛出异常
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 在sleep、join、wait等方法造成线程阻塞时，如果收到中断信号，则会清除中断状态
                System.out.println("当前线程中断状态：" + Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }


}

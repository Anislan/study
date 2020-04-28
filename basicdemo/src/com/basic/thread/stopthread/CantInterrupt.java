package com.basic.thread.stopthread;

/**
 * 如果while里面放try/catch，会导致中断失效
 */
public class CantInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;

            //这个检测已经没啥效果了，主要由sleep睡眠时就能监听中断信号了
//            while (num<= 10000 & !Thread.currentThread().isInterrupted()){
            while (num <= 10000) {
                try {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                    // 一旦sleep响应中断，就会把interrupt标记位给清除
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}

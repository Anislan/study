package com.basic.thread.juc.threadpool.simple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  OOM异常展示
 *  设置Xms 8m Xmx 8
 */
public class FixedThreadPollOOM {

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(new SubThread());
        }
    }
}

class  SubThread implements  Runnable{

    @Override
    public void run() {
        try {
            System.out.println("SubThread ... 正在执行");
            Thread.sleep(10);
           // Thread.sleep(1000000000);
        }catch (InterruptedException  e){

        }
    }
}

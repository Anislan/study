package com.basic.thread.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {

    public static void main(String[] args) throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        ExecutorService executorService= Executors.newFixedThreadPool(20);
        long startTimt = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            executorService.submit(new LongAdderDemo.Task(longAdder));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()){

        }
        long endTimt = System.currentTimeMillis();
//        Thread.sleep(10000);
        System.out.println(longAdder.sum());

        System.out.println("LongAdder耗时:"+(endTimt-startTimt));
    }

    private static class Task implements Runnable{

        private LongAdder longAdder;

        public Task(LongAdder longAdder) {
            this.longAdder = longAdder;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                longAdder.increment();
            }
        }
    }
}

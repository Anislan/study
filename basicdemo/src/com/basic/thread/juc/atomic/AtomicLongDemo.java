package com.basic.thread.juc.atomic;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 *  高并发场景下，LongAdder比AtomicLong性能更好
 */
public class AtomicLongDemo {

    public static void main(String[] args) throws InterruptedException {
        AtomicLong atomicLong = new AtomicLong(0);
        ExecutorService executorService=Executors.newFixedThreadPool(20);
       long startTimt = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            executorService.submit(new Task(atomicLong));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()){

        }
        long endTimt = System.currentTimeMillis();
//        Thread.sleep(10000);
        System.out.println(atomicLong.get());

        System.out.println("AtomicLong耗时:"+(endTimt-startTimt));
    }

    private static class Task implements Runnable{

        private AtomicLong counter;

        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
            }
        }
    }
}

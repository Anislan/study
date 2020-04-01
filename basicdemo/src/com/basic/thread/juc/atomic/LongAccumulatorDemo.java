package com.basic.thread.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 *
 */
public class LongAccumulatorDemo {

    public static void main(String[] args) {
        // 100是对x的初始值
        LongAccumulator longAccumulator = new LongAccumulator((x, y) -> Math.max(x,y), 0);
        // y=x（identity）+x（1）
//        longAccumulator.accumulate(1);
//        longAccumulator.accumulate(2);
       ExecutorService executorService= Executors.newFixedThreadPool(8);
        IntStream.range(1,10).forEach(i ->{
            executorService.submit(() -> longAccumulator.accumulate(i));
        });
        executorService.shutdown();
        while (!executorService.isTerminated()){

        }
        System.out.println(longAccumulator.getThenReset());
    }
}

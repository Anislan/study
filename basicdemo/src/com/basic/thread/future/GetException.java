package com.basic.thread.future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 *  get方法过程中抛出异常，for循环为了演示抛出Exception的时机：并不是说一产生异常就抛出，
 *  直到我们get执行时，才会抛出
 */
public class GetException {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
            Future<Integer> future=executorService.submit(new CallableTask());
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(500);
            }
            // 结果为true，任务抛异常了
            System.out.println(future.isDone());
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException异常");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("ExecutionException异常");

        }
    }

    static class CallableTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
           throw new IllegalArgumentException("Callable抛出异常");
        }
    }
}

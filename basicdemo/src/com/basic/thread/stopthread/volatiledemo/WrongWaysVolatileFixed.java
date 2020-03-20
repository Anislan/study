package com.basic.thread.stopthread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用中断来修复刚才无尽等待问题
 */
public class WrongWaysVolatileFixed {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);

        Producer1 producer = new Producer1(arrayBlockingQueue);
        Thread producerThread = new Thread(producer);
        producerThread.start();

        //睡眠一会，让队列满
        Thread.sleep(2000);

        Consumer1 consumer = new Consumer1(arrayBlockingQueue);
        while (consumer.needMoreNums()){
            System.out.println(consumer.storage.take()+"被消费了");
            Thread.sleep(300);
        }
        System.out.println("消费者不需要更多数据了");

        // 一旦消费不需要更多数据了，我们应该让生产者也停下来，但是实际情况
//        producer.canceled = true;
        producerThread.interrupt();
//        Thread.interrupted();
//            producerThread.isInterrupted();
//        System.out.println(  producer.canceled );
    }
}


class  Producer1 implements  Runnable{

    // 对这个变量具有可见性（多个线程中）
    public   volatile  boolean canceled = false;

    BlockingQueue storage ;

    public Producer1(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
//            while (num <=100000 && !canceled){
                while (num <=100000 && !Thread.currentThread().isInterrupted()){
                if(num % 100 == 0){
                    // 当队列满了之后，这里会发生阻塞
                    storage.put(num);
                    System.out.println(num+"是100的倍数,被放到仓库中");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("生产者停止运行！");
    }
}

class Consumer1 {
    BlockingQueue storage;

    public Consumer1(BlockingQueue storage) {
        this.storage = storage;
    }
    public boolean needMoreNums(){
        if (Math.random() > 0.95){
            return  false;
        }
        return  true;
    }
}


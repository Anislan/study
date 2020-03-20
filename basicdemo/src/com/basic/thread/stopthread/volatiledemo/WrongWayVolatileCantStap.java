package com.basic.thread.stopthread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * volatile的局限，当陷入了阻塞时，volatile是无法停止线程的
 * 此例中，生产者的生产速度很快，消费者消费速度慢
 * 所以阻塞队列满了以后，生产者会阻塞，等待消费者进一步消费
 */
public class WrongWayVolatileCantStap {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);

        Producer producer = new Producer(arrayBlockingQueue);
        Thread producerThread = new Thread(producer);
        producerThread.start();

        //睡眠一会，让队列满
        Thread.sleep(2000);

        Consumer consumer = new Consumer(arrayBlockingQueue);
        while (consumer.needMoreNums()){
            System.out.println(consumer.storage.take()+"被消费了");
            Thread.sleep(300);
        }
        System.out.println("消费者不需要更多数据了");

        // 一旦消费不需要更多数据了，我们应该让生产者也停下来，但是实际情况
        producer.canceled = true;
        System.out.println(  producer.canceled );
    }
}


class  Producer implements  Runnable{

    // 对这个变量具有可见性（多个线程中）
    public   volatile  boolean canceled = false;

    BlockingQueue storage ;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <=100000 && !canceled){
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

class Consumer {
 BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }
    public boolean needMoreNums(){
        if (Math.random() > 0.95){
            return  false;
        }
        return  true;
    }
}

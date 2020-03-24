package com.basic.thread.threadobjectmethods;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *  用wait/notify来实现
 */
public class ProducerConsumerModel {

    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage();
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

}

class Producer implements  Runnable{
    private  EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.put();
        }
    }
}

class Consumer implements  Runnable{
    private  EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.take();
        }
    }
}

class  EventStorage{
    private int maxSize;
    private LinkedList<Date> stoage;

    public EventStorage(){
        maxSize =10;
        stoage = new LinkedList<>();
    }

    public  synchronized void put(){
        while (stoage.size() == maxSize){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stoage.add(new Date());
        System.out.println("仓库里有了"+stoage.size()+"个产品。");
        notify();
    }

    public  synchronized void take(){
        while (stoage.size()==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("拿到了"+stoage.poll()+",现在仓库还剩下"+stoage.size());
        notify();
    }
}

package com.basic.thread.synchronize.seven;

public class VariableAddAdd implements Runnable {

    static  int i = 0;
    static VariableAddAdd instance = new VariableAddAdd();

//    @Override
//    public synchronized void run() {
//        for (int j = 0; j < 100000; j++) {
//            i++;
//        }
//    }
    @Override
    public  void run() {
//        synchronized(this){
//            for (int j = 0; j < 100000; j++) {
//                i++;
//            }
//        }

        synchronized(VariableAddAdd.class){
            for (int j = 0; j < 100000; j++) {
                i++;
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        System.out.println();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("i:"+i);
    }
}

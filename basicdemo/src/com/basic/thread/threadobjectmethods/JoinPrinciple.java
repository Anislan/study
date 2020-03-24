package com.basic.thread.threadobjectmethods;

/**
 *  通过join原理，分析出join的代替写法
 */
public class JoinPrinciple {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"执行完毕。");
            }
        });



        thread1.start();
        System.out.println("开始等待子线程运行完毕");
//        thread1.join();
        // 等价代码=join
        synchronized (thread1){
            thread1.wait();
        }
        System.out.println("所有线程执行完毕");
    }
}

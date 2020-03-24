package com.basic.thread.threadobjectmethods;

/**
 *  wait和notify的基本用法
 *  1.关注代码执行顺序
 *  2.证明wait释放锁
 */
public class Wait {

    public static  Object object = new Object();

    static  class  Thread1 extends  Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(Thread.currentThread().getName()+"开始执行了");
                try {
                    // 如果遇到中断就会抛出异常.释放掉锁了,并且阻塞状态
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"获取到锁了");
            }
        }
    }

    static  class  Thread2 extends  Thread{
        @Override
        public void run() {
            synchronized (object){
                object.notify();
                System.out.println(Thread.currentThread().getName()+"调用了notify（）");
            }
        }
    }

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}

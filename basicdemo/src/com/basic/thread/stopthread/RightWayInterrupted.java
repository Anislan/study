package com.basic.thread.stopthread;

/**
 * Thread.interrupted(）方法的目标对象是"当前线程"，而不管本方法来自于哪个对象
 */
public class RightWayInterrupted {

    public static void main(String[] args)  {


        Thread thread = new Thread(new ThreadOne());
        thread.start();
        // 中断线程
        thread.interrupt();

        //获取中断标志
        while (thread.isInterrupted())
            System.out.println("IsInterrupted:"+thread.isInterrupted());
            //获取中断标志并重置
            System.out.println("IsInterrupted:"+Thread.currentThread().getName()+ ","+Thread.interrupted());
            //获取中断标志并重置
            System.out.println("IsInterrupted:"+Thread.currentThread().getName()+ ","+thread.interrupted());

            //获取中断标志
            System.out.println("IsInterrupted:"+thread.isInterrupted());

            System.out.println("Main thread is over");


    }

}

class  ThreadOne implements  Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程运行中...");
    }
}

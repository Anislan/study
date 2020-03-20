package com.basic.thread.stopthread;

/**
 * run(）方法内没有sleep或wait方法时，停止线程
 */
public class RightWayStopThreadWithoutSleep implements Runnable{


    @Override
    public void run() {
        int num = 0;
        // 线程不会中断
//        while (num < Integer.MAX_VALUE/2){
            // 检测线程是否发生中断状态
            while (num < Integer.MAX_VALUE/2 && !Thread.currentThread().isInterrupted()){
            if(num % 10000 == 0){
                System.out.println(num+"是10000的倍数");
            }
            num++;
        }
        System.out.println("任务运行结束了");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}

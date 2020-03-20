package com.basic.thread.stopthread;

/**
 * 最佳实践2：在catch子语句中调用Thread.cunrrentThread().interrupt()来恢复
 * 中断状态，以便于在后续的执行中，依然能够检查到刚才发生了中断
 */
public class RightWayStopThreadProd implements  Runnable{

    @Override
    public void run() {
        while (true){
            // 发生中断，则程序跳出循环
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interrupted，程序运行结束");
                break;
            }
            System.out.println("busniss logic...");
            throwInMethod(); }

    }

    private void throwInMethod()  {


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // 正确的方式，捕获异常后，重新设置中断标识
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        // 错误的方式
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(new RightWayStopThreadProd());
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}

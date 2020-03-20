package com.basic.thread.stopthread;

/**
 * 最佳实践：catch了InterruptException之后的优先选择：在方法签名中抛出异常
 * 那么就在run()就会强制try/catch

 */
public class RightWayStopThreadProd2 implements  Runnable{

    @Override
    public void run() {
        try {
        while (true){
            System.out.println("busniss logic...");

                throwInMethod(); }
        } catch (InterruptedException e) {
            // 保存日志、停止程序
            e.printStackTrace();
        }
    }

    private void throwInMethod() throws InterruptedException {

        // 正确的方式，抛出异常
        Thread.sleep(1000);

        // 错误的方式
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(new RightWayStopThreadProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}

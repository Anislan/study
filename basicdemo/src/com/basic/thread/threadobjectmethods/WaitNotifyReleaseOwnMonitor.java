package com.basic.thread.threadobjectmethods;

/**
 *  wait只释放当前的那把锁
 */
public class WaitNotifyReleaseOwnMonitor {

    private static volatile  Object reourceA = new Object();
    private static volatile  Object reourceB = new Object();

    public static void main(String[] args) {
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (reourceA){
                    System.out.println("ThreadA got resourceA lock.");
                    synchronized (reourceB){
                        System.out.println("ThreadA got resourceB lock.");
                        try {
                            System.out.println("ThreadA releases resoureceA lock.");
                            reourceA.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (reourceA){
                    System.out.println("ThreadB got reourceA lock.");
                    System.out.println("ThreadB tries to reourceB lock.");
                    synchronized (reourceB){
                        System.out.println("ThreadB got reourceB lock.");
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }

}

package com.basic.thread.jmm;

/**
 *  可见性带来的问题
 */
public class FieldVisibility {

//    int a= 1;
//    int b= 2;

    // volatile解决该问题
    volatile int a= 1;
    volatile int b= 2;

    public static void main(String[] args) {
        while(true) {
            FieldVisibility fieldVisibility = new FieldVisibility();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    fieldVisibility.change();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    fieldVisibility.print();
                }
            }).start();
        }

    }
    // 符合单线程原则，
    private void change() {
        a= 3;
        b=a;
    }

    private void print(){
        System.out.println("b="+b+",a="+a);
    }

}

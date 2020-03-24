package com.basic.thread.singleton;

/**
 *  双重检查（线程安全，推荐面试使用）
 */
public class Singleton6 {

    // 保证instance可见性和重排序。
    // 主要是由于创建对象底层是有三个步骤的，CPU和编译器可能会这三个步骤进行重排序，则容易造成空指针
    private volatile static Singleton6 instance;

    private Singleton6(){

    }

    public  static Singleton6 getInstance(){
        if (instance == null){
            synchronized (Singleton6.class){
                if(instance == null){
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}

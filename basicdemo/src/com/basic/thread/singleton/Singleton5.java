package com.basic.thread.singleton;

/**
 *  懒汉式（线程不安全）,懒->使用时才加载
 */
public class Singleton5 {

    private  static Singleton5 instance;

    private Singleton5(){

    }

    /**
     * 同步代码块
     * @return
     */
    public  static Singleton5 getInstance(){
        // 两个线程同时判断为null，都去创建实例，则创建两个，不符合单例模式
        if(null == instance){
            synchronized(Singleton5.class){
                instance = new Singleton5();
            }
        }
        return instance;
    }
}

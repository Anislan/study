package com.basic.thread.singleton;

/**
 *  懒汉式（线程安全）,懒->使用时才加载
 */
public class Singleton4 {

    private  static Singleton4 instance;

    private Singleton4(){

    }

    /**
     * 缺点：效率太低
     * @return
     */
    public synchronized static Singleton4 getInstance(){
        // 两个线程同时判断为null，都去创建实例，则创建两个，不符合单例模式
        if(null == instance){
            instance = new Singleton4();
        }
        return instance;
    }
}

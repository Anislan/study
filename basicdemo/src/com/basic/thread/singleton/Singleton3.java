package com.basic.thread.singleton;

/**
 *  懒汉式（线程不安全）,懒->使用时才加载
 */
public class Singleton3 {

    private  static Singleton3 instance;

    private Singleton3(){

    }

    public static Singleton3 getInstance(){
        // 两个线程同时判断为null，都去创建实例，则创建两个，不符合单例模式
        if(null == instance){
            instance = new Singleton3();
        }
        return instance;
    }
}

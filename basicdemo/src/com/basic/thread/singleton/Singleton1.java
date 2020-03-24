package com.basic.thread.singleton;

/**
 *  饿汉式（静态常量）（可选）,饿是在类加载的时候
 *  优点：
 *  1.写法简单
 *  2.在类加载的时候，已经完成实例化（static）
 *
 */
public class Singleton1 {

    private final  static  Singleton1 INSTANCE = new Singleton1();

    private Singleton1(){

    }

    public  static Singleton1 getInstance(){
        return INSTANCE;
    }
}

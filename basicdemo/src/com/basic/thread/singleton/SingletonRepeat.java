package com.basic.thread.singleton;

/**
 *  懒汉
 *  饿汉
 *  枚举
 */
public class SingletonRepeat {

    private SingletonRepeat(){

    }

    // 1.饿汉-静态常量（可选）
    private  static final SingletonRepeat singletonRepeat = new SingletonRepeat();



    public static SingletonRepeat newInstanceByConstant(){
        return singletonRepeat;
    }

    // 2.饿汉-静态代码块（可用）
    private  static final SingletonRepeat singletonRepeat1;

    static {
        singletonRepeat1 = new SingletonRepeat();
    }

    public static SingletonRepeat newInstanceByBlock(){
        return singletonRepeat;
    }


    // 3.懒汉-常规写法（不加同步,线程不安全,容易创建两个对象实例）
    private static  SingletonRepeat singletonRepeat2;

    public static SingletonRepeat newInstanceByLazy(){
        if(singletonRepeat2==null){
            singletonRepeat2 = new SingletonRepeat();
        }
        return singletonRepeat;
    }

    // 4.懒汉-同步方法（效率低）
    private static SingletonRepeat singletonRepeat3;

    public synchronized static  SingletonRepeat  newInstanceBySyn(){
        if(singletonRepeat3 == null){
            singletonRepeat3 = new SingletonRepeat();
        }
        return singletonRepeat3;
    }

    // 5.懒汉-同步代码块（线程不安全，容易创建出一个以上的实例）
    private static SingletonRepeat singletonRepeat4;

    public static SingletonRepeat newInstanceSynBlock(){

            if (null == singletonRepeat4){
                synchronized (SingletonRepeat.class){
                    singletonRepeat4 = new SingletonRepeat();
                }
            }
            return singletonRepeat4;
    }

    // 6.懒汉-双重校验
    private static volatile SingletonRepeat singletonRepeat5;

    public static SingletonRepeat newInstanceSynBlockTwo(){
        if (null == singletonRepeat5){
            synchronized (SingletonRepeat.class){
                if(null == singletonRepeat5){
                    singletonRepeat5 = new SingletonRepeat();
                }
            }
        }
        return singletonRepeat5;
    }

    // 7.懒汉-静态内部类
    private static class SingletonStaticClass{
        private static final  SingletonRepeat singletonRepeat6 = new SingletonRepeat();
    }

    public static SingletonRepeat newInstanceStaticClass(){
        return SingletonStaticClass.singletonRepeat6;
    }
}

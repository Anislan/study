package com.basic.thread.synchronize.recursion;

/**
 *  可重入粒度测试：
 *  1.递归调用本方法
 *  2.调用类内另外的方法
 *  3.证明可重入要求不是同一个类的方法
 */
public class SynchronizedRecursion {

    int a= 0;

    public static void main(String[] args) {
        SynchronizedRecursion synchronizedRecursion = new SynchronizedRecursion();
        synchronizedRecursion.sync();

        // 验证调用类内另外的方法
        synchronizedRecursion.method1();

        // 调用父类的方法
        TestClass testClass = new TestClass();
        testClass.doSomething();
    }

    private synchronized  void method1(){
        System.out.println("我是method1");
        method2();
    }
    private synchronized  void method2(){
        System.out.println("我是method2");
    }


    /**
     *  递归调用本身的方法是可以的
     */
    private synchronized void sync() {
        System.out.println("这是sync,a="+a);
        if(a==0){
            a++;
            sync();
        }
    }
}
class SynchronizedSuperClass {

    public  synchronized  void doSomething(){
        System.out.println("我是父类方法");
    }
}

class TestClass extends SynchronizedSuperClass{
    @Override
    public synchronized void doSomething() {
        System.out.println("我是子类的方法");
        super.doSomething();
    }
}

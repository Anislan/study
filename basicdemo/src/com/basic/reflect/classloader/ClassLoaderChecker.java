package com.basic.reflect.classloader;

public class ClassLoaderChecker {

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader("myClassLoader","C:\\Program Files\\Java\\jdk1.8.0_91\\1\\");
//        Class loadClass=myClassLoader.loadClass("RebootSon1");
//        System.out.println(loadClass.getClass());
        // 自定义ClassLoader父类是APPClassLoader
        System.out.println(myClassLoader.getParent().getClass());
        // APPClassLoader父类是EXTClassLoader
        System.out.println(myClassLoader.getParent().getParent().getClass());
        // EXTClassLoader的父类BotstapClassLoader
        System.out.println(myClassLoader.getParent().getParent().getParent());
    }
}

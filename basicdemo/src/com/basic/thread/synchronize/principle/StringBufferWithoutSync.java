package com.basic.thread.synchronize.principle;

public class StringBufferWithoutSync {


    public void  add(String str1,String str2){
        // StringBuffer是线程安全，由于stringBuffer只会在append方法中使用，不可能被其他线程引用
        // 因此stringBuffer属于不可能共享的资源，JVM会自动消除内部的锁
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str1).append(str2);
    }

    public static void main(String[] args) {
        StringBufferWithoutSync stringBufferWithoutSync = new StringBufferWithoutSync();
        for (int i = 0; i < 10000; i++) {
                stringBufferWithoutSync.add("aaa","bbb");
        }
    }
}

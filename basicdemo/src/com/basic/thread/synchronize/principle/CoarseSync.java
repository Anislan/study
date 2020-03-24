package com.basic.thread.synchronize.principle;

public class CoarseSync {

    public static String copyString100Times(String target){
        int i = 0 ;
        StringBuffer sb = new StringBuffer();
        while (i < 100){
            // 连续对同一个对象反复加锁，JVM会自动扩大加锁的范围
            sb.append(target);
        }
        return sb.toString();
    }
}

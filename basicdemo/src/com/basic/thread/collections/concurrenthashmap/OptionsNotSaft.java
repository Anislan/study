package com.basic.thread.collections.concurrenthashmap;


import java.util.concurrent.ConcurrentHashMap;

/**
 *  组合操作并不保证线程安全
 */
public class OptionsNotSaft implements Runnable{

    private static ConcurrentHashMap<String,Integer> socres = new ConcurrentHashMap<String,Integer>();

    public static void main(String[] args) throws InterruptedException {
        socres.put("小明",0);
        Thread t1=new Thread(new OptionsNotSaft());
        Thread t2=new Thread(new OptionsNotSaft());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(socres);

    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
//            synchronized (OptionsNotSaft.class){
            while (true){
                // 只能保证get安全
                Integer socre =  socres.get("小明");
                // 组合操作
                Integer newScore = socre+1;

                //replace方法,返回结果是false，则不修改操作
                boolean b=socres.replace("小明",socre,newScore);
                if (b){
                    break;
                }
            }


                // 只能保证put安全
//                socres.put("小明",newScore);
//            }

        }
    }
}

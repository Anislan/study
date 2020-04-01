package com.basic.thread.future;

import java.util.concurrent.*;

/**
 *  演示get的超时方法，需要注意超时后处理，调用futur.cancel()。演示cancle传入true和false的区别，
 *  代表是否中断正在执行的任务。
 */
public class TimeOut {

    private static final Ad DEFAULT_AD = new Ad("无网络时候的默认广告");

    private static final ExecutorService servie = Executors.newFixedThreadPool(10);

    static  class  Ad{
        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class FetchAdTask implements Callable<Ad>{

        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                System.out.println("sleep期间被中断了");
                return new Ad("被中断时候的默认广告");
            }
            return new Ad("xxx广告");
        }
    }

    public void printAd(){
        Future<Ad> adFuture = servie.submit(new FetchAdTask());
        Ad ad;
        try {
            ad=adFuture.get(2,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            ad = new Ad("被中断时候的默认广告");
        } catch (ExecutionException e) {
            ad = new Ad("异常时候的默认广告");
        } catch (TimeoutException e) {
            ad = new Ad("超时时候的默认广告");
            System.out.println("超时，未获取到广告");
            // 传入true，会发出一个中断信号
            // cancle方法不需要future返回的结果
            boolean cancel=adFuture.cancel(false);
            System.out.println("cancle的结果："+cancel);
            servie.shutdown();
            System.out.println(ad);
        }
    }

    public static void main(String[] args) {
        TimeOut timeOut = new TimeOut();
        timeOut.printAd();
    }
}

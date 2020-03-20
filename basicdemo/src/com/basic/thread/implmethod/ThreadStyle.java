package com.basic.thread.implmethod;

public class ThreadStyle extends  Thread{
    @Override
    public void run() {
        System.out.println("extend Thread Method......");
    }

    public static void main(String[] args) {
        Thread thread = new ThreadStyle();
        thread.start();
    }
}

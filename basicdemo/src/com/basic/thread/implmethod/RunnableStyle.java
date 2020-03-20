package com.basic.thread.implmethod;

public class RunnableStyle implements  Runnable {
    @Override
    public void run() {
        System.out.println("implement Runnable Method");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }
}

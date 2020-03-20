package com.basic.thread.startthread;

/**
 * 两次start(）线程
 */
public class CantStartTwice {

    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}

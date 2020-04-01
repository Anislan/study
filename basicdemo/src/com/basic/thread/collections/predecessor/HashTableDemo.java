package com.basic.thread.collections.predecessor;

import java.util.Hashtable;

public class HashTableDemo {

    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("xx","50%");
        System.out.println(hashtable.get("xx"));
    }
}

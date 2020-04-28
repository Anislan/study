package com.basic.thread.collections.concurrenthashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        Map<String,Object> map=new HashMap<>(1);
        map.put("key1","value1");
        map.put("key2","value1");
        map.put("key3","value1");
        map.put("key4","value1");
        map.put("key5","value1");
        map.put("key6","value1");



//        new ConcurrentHashMap<>();
    }
}

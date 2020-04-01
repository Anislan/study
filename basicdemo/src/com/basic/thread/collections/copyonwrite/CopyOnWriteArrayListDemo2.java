package com.basic.thread.collections.copyonwrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *  对比两个迭代器
 */
public class CopyOnWriteArrayListDemo2 {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3});
        System.out.println(list);

        // 迭代器1拿到仍然是旧的数据
        Iterator<Integer> iterator1=list.iterator();

        list.add(4);
        System.out.println(list);

        Iterator<Integer> iterator2=list.iterator();

        iterator1.forEachRemaining(System.out::println);
        iterator2.forEachRemaining(System.out::println);

    }
}

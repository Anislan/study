package com.basic.thread.collections.predecessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  Collections.synchronizedList()
 */
public class SynList {
    public static void main(String[] args) {
        List<Integer> list=Collections.synchronizedList(new ArrayList<Integer>());
        list.add(5);
        System.out.println(list.get(0));
    }
}

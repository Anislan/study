package com.basic.thread.collections.copyonwrite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *  CopyOnWriteArrayList可以在迭代的过程中修改数组内存，但是ArrayList不行的
 *  但是修改之后，CopyOnWriteArrayList在迭代过程值没有更改，仍旧是迭代以前list
 *
 */
public class CopyOnWriteArrayListDemo1 {

    public static void main(String[] args) {
//        ArrayList arrayList = new ArrayList<Integer>();

        CopyOnWriteArrayList<Integer> arrayList = new CopyOnWriteArrayList<>();

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()){
            System.out.println("list is"+arrayList);
            Integer integer=iterator.next();
            System.out.println(integer);
            if(integer.intValue() == 2){
                arrayList.remove(4);
            }
            if (integer.intValue() == 3){
                    arrayList.add(6);
            }
        }


    }
}

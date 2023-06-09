package com.mad.juc.thread.collection;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteListTest {

    public static void main(String[] args) {


        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        new Thread(()->{
            list.remove(0);
            System.out.println(list);
        }).start();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }
}

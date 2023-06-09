package com.mad.juc.thread;

import java.util.concurrent.locks.LockSupport;

public class ParkDemo {

    public static void main(String[] args) {


        Thread t1 = new Thread(()->{

            LockSupport.park();

        },"t1");
        t1.start();
    }
}

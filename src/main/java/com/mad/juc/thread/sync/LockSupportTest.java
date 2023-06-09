package com.mad.juc.thread.sync;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    static Thread t1;
    static Thread t2;
    static Thread t3;
    
    public static void main(String[] args) {

         t1 = new Thread(() -> print("a", t2));
        t2 = new Thread(() -> print("b", t3));
        t3 = new Thread(() -> print("c", t1));
        t1.start();
        t2.start();
        t3.start();
        LockSupport.unpark(t1);
    }

    public static void print(String content,Thread nextThread){

        for(int i=0;i<5;i++){

            LockSupport.park();
            System.out.print(content);
            LockSupport.unpark(nextThread);

        }


    }

}

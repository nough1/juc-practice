package com.mad.juc.thread.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentranlockTest extends ReentrantLock {

   static ReentranlockTest reentranlockTest = new ReentranlockTest();
    public static void main(String[] args) {

        Condition c1 = reentranlockTest.newCondition();
        Condition c2 = reentranlockTest.newCondition();
        Condition c3 = reentranlockTest.newCondition();

        new Thread(()->reentranlockTest.print("a",c1,c2)).start();
        new Thread(()->reentranlockTest.print("b",c2,c3)).start();
        new Thread(()->reentranlockTest.print("c",c3,c1)).start();

        reentranlockTest.lock();

        try {
            c1.signal();
        }finally {
            reentranlockTest.unlock();
        }

    }

    public static void print(String content, Condition condition,Condition nextCondition){

        for(int i=0;i<5;i++){

            reentranlockTest.lock();

            try {
                condition.await();
                System.out.print(content);
                nextCondition.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                reentranlockTest.unlock();
            }

        }


    }
}

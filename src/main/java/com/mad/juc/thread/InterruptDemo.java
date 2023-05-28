package com.mad.juc.thread;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {

        // 用来线程之间的同步，当发起方发起 interrupt 了之后，被发起方检查这个interrupt 标记，进行相应处理.
        Thread t1 = new Thread(()->{

                while (true) {
                    if(Thread.currentThread().isInterrupted()){
                        break;
                    }
                    System.out.println("hello");
                }
        },"t1");
        t1.start();

        t1.interrupt();


    }
}

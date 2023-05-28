package com.mad.juc.thread;

public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            public void run() {
                System.out.println("t1");
            }
        }).start();

        Thread t1 = new Thread(() -> {
            System.out.println("hello");
        });
        t1.join();
        System.out.println("main");
    }
}

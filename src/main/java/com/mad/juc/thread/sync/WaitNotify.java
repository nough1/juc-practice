package com.mad.juc.thread.sync;

public class WaitNotify {

    public static void main(String[] args) {

        PrintTask printTask = new PrintTask();
        new Thread(()->printTask.print("a",1,2),"t1").start();
        new Thread(()->{printTask.print("b",2,3);},"t2").start();
        new Thread(()->{printTask.print("c",3,1);},"t3").start();

    }

    static class PrintTask{

        public int currentCondition=1;

        public void print(String content,int condition,int nextCondition)  {

            for(int i=0;i<5;i++){

                synchronized (this){


                    while (this.currentCondition !=condition){


                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            System.out.println("interupt:"+content);
                            throw new RuntimeException(e);
                        }

                        System.out.println("thread:"+Thread.currentThread().getName()+",notified:"+nextCondition);
                    }
                    // condition ready
                    System.out.println("thread:"+Thread.currentThread().getName()+",print"+content);

                    // notice next thread

                    currentCondition = nextCondition;
                    this.notifyAll();

                }
            }


        }

    }
}

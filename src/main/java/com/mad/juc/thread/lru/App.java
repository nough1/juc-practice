package com.mad.juc.thread.lru;

import javax.print.DocFlavor;
import java.util.*;

public class App {

    class Node{

        public String key;
        public String value;
        public Long accessTime;

        public Node next ;

        public Node pre;

    }

    static Node head ,tail;

    static int cap = 3;

    // head is the oldest , tail is the newest

    static Map<String,Node> map = new HashMap<>();


    public static void main(String[] args) {

        App app = new App();

        app.put("a","1");
        app.print();
        System.out.println(app.get("a"));
        app.print();
        app.put("b","2");
        app.print();
        app.put("c","3");
        app.print();

        app.put("d","4");
        app.print();

        System.out.println(app.get("a"));
        app.print();
        System.out.println(app.get("b"));
        app.print();
        System.out.println(app.get("d"));
        app.print();


    }

    public void remove(Node node ){

        // 1-2->3

        Node pre = node.pre ;
        Node next = node.next;
        if(pre==null){
/*

            if(next==null){
                head = null;
                tail = null;
            }else{

                removeHead();

            }
*/
            removeHead();

        }else{


                pre.next =next;
                next.pre = pre;




        }



        // deal with one node

    }

    public void insertTail(Node node){

        // 1. tail next is node
        // tail is the latest node
        if(tail==null){

            tail = node;
            head = node;

        }else{

            tail.next = node ;
            node.pre = tail;
            tail = node;


        }



    }

    public  void print(){


        Node current = head;
        StringBuilder sb = new StringBuilder();

        while(current!=null){


            sb.append(current.key);
            current = current.next;

        }
        current = tail;
        StringBuilder pre = new StringBuilder();
        while(current!=null){


            pre.append(current.key);
            current = current.pre;

        }
        System.out.println("list:"+sb.toString()+",pre:"+pre.toString());
    }

    public void removeHead(){

        Node next = head.next;
        if(next!=null){
            next.pre = null;
        }else{
            tail = null;
        }

        head.next=null;
        head = next;


    }
    public  String get(String key){

        // if exist ,update access time ( remove , insert to head ) return
        Node node = map.get(key);
        if(map.containsKey(key)) {

            remove(node);
            insertTail(node);
            return node.value;
        }

        return null;
    }

    public void put(String key,String value){

        // if key exist , remove and insert tail  ,

        Node node = map.get(key);
        if(node!=null){
            node.value = value ;
            remove(node);
            insertTail(node);
            return ;
        }

        // if not key exist ,

        Node n1 = new Node();
        n1.key=key;
        n1.value=value;



        if(map.size()<cap){

            // if capcity not full , put , insertTail

            map.put(key,n1);
            insertTail(n1);

        }else{

            // if cap full , remove queue head , put the key value , insert the queue tail

            map.remove(head.key);
            removeHead();

            map.put(key,n1);

            insertTail(n1);


        }




    }


}

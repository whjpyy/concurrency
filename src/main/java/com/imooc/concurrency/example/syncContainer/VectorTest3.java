package com.imooc.concurrency.example.syncContainer;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class VectorTest3 {

    // java.util.ConcurrentModificationException
    public static void test1(List<Integer> list){ // foreach
        for(Integer i : list){
            if(i.equals(3)){
                list.remove(i);
            }
        }
    }

    // java.util.ConcurrentModificationException
    public static void test2(List<Integer> list){ // iterator
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()){
            Integer i = it.next();
            if(i.equals(3)){
                list.remove(i);
            }
        }
    }

    public static void test3(List<Integer> list){ // list
        for(Integer i = 0;i < list.size();i ++){
            if(i.equals(3)){
                list.remove(i);
            }
        }
    }


    public static void main(String[] args) {
        List<Integer> list = new Vector<>();
        list.add(1);
        list.add(2);
        list.add(3);
        test3(list);
    }
}

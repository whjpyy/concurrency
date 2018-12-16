package com.imooc.concurrency.example.singleton;

import com.imooc.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 */
@ThreadSafe
public class SingletomExample5 {

    private SingletomExample5(){

    }

    // 单例对象 必须在static前面
    private static SingletomExample5 instance = null;

    static {
        instance = new SingletomExample5();
    }

    public static SingletomExample5 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
        System.out.println(getInstance());
    }
}

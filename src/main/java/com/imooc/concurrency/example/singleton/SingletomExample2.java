package com.imooc.concurrency.example.singleton;

import com.imooc.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 */
@ThreadSafe
public class SingletomExample2 {

    private SingletomExample2(){

    }

    // 单例对象
    private static SingletomExample2 instance = new SingletomExample2();

    public static SingletomExample2 getInstance(){
        return instance;
    }
}

package com.imooc.concurrency.example.singleton;

import com.imooc.concurrency.annotations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingletomExample1 {

    private SingletomExample1(){

    }

    // 单例对象
    private static SingletomExample1 instance = null;

    public static SingletomExample1 getInstance(){
        if(instance == null){
            return new SingletomExample1();
        }
        return instance;
    }
}

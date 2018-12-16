package com.imooc.concurrency.example.singleton;

import com.imooc.concurrency.annotations.NotThreadSafe;
import com.imooc.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式 -> 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 */
@ThreadSafe
public class SingletomExample4 {

    private SingletomExample4(){

    }

    // 单例对象 volatile + 双重检测机制 => 禁止cpu进行指令重排
    private static volatile SingletomExample4 instance = null;

    // 静态的工厂方法
    public static SingletomExample4 getInstance(){
        if(instance == null){ // 双重检测机制   B
            synchronized (SingletomExample4.class){
                if(instance == null){
                    // 1. memory = allocate() 分配对象的内存空间
                    // 2. ctorInstance 初始化对象
                    // 3. instance = memory 设置instance指向刚分配的内存

                    // JVM和cpu优化，发生了指令重排

                    // 1. memory = allocate() 分配对象的内存空间
                    // 3. ctorInstance 初始化对象
                    // 2. instance = memory 设置instance指向刚分配的内存
                    instance = new SingletomExample4(); // A - 3
                }
            }
        }
        return instance;
    }
}

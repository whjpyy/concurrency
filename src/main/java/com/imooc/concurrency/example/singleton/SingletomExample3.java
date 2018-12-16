package com.imooc.concurrency.example.singleton;

import com.imooc.concurrency.annotations.NotRecommend;
import com.imooc.concurrency.annotations.NotThreadSafe;
import com.imooc.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 */
@ThreadSafe
@NotRecommend
public class SingletomExample3 {

    private SingletomExample3(){

    }

    // 单例对象
    private static SingletomExample3 instance = null;

    public synchronized static SingletomExample3 getInstance(){
        if(instance == null){
            return new SingletomExample3();
        }
        return instance;
    }
}

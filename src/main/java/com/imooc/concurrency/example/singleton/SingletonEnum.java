package com.imooc.concurrency.example.singleton;

import com.imooc.concurrency.annotations.Recommend;
import com.imooc.concurrency.annotations.ThreadSafe;

/**
 * 枚举模式：最安全
 */
@ThreadSafe
@Recommend
public class SingletonEnum {

    private SingletonEnum(){

    }

    public static SingletonEnum getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonEnum singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton(){
            singleton = new SingletonEnum();
        }
        public SingletonEnum getInstance(){
            return  singleton;
        }
    }
}

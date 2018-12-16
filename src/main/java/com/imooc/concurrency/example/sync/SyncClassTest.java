package com.imooc.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SyncClassTest {

    // 修饰一个代码块
    public static void test1(int j){
        synchronized (SyncClassTest.class){
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {} - {}", j, i);
            }
        }
    }

    // 修改一个方法
    public static synchronized void test2(int j){
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SyncClassTest test1 = new SyncClassTest();
        SyncClassTest test2 = new SyncClassTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            test1.test1(1);
        });
        executorService.execute(() -> {
            test2.test1(2);
        });
        executorService.shutdown();
    }
}

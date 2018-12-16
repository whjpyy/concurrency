package com.imooc.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SyncObjectTest {

    // 修饰一个代码块
    public void test1(int j){
        synchronized (this){
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {} - {}", j, i);
            }
        }
    }

    // 修改一个方法
    public synchronized void test2(int j){
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SyncObjectTest test1 = new SyncObjectTest();
        SyncObjectTest test2 = new SyncObjectTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            test1.test2(1);
        });
        executorService.execute(() -> {
            test2.test2(2);
        });
    }
}

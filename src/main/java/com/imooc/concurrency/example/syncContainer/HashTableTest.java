package com.imooc.concurrency.example.syncContainer;

import com.imooc.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class HashTableTest {

    // 请求总数
    private static int clientTotal = 5000;
    // 同时并发执行的线程总数
    private static int threadTotal = 200;

    private static Map<Integer, Integer> map = new Hashtable<>();
    private static void update(final int c){
        map.put(c, c);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0;i < clientTotal;i ++){
            final int c = i;
            executorService.execute( () ->{
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    log.error("exception:", e);
                }
                update(c);
                semaphore.release();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size: {}", map.size());
    }

}

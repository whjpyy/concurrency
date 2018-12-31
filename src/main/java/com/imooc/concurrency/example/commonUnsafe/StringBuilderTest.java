package com.imooc.concurrency.example.commonUnsafe;

import com.imooc.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class StringBuilderTest {

    // 请求总数
    private static int clientTotal = 5000;
    // 同时并发执行的线程总数
    private static int threadTotal = 200;

    private static StringBuilder stringBuilder = new StringBuilder();
    private static void update(){
        stringBuilder.append("1");
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0;i < clientTotal;i ++){
            executorService.execute( () ->{
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    log.error("exception:", e);
                }
                update();
                semaphore.release();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size: {}", stringBuilder.length());
    }

}

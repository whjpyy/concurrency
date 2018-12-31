package com.imooc.concurrency.example.concurren;

import com.imooc.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class CopyOnWriteArraySetTest {

    // 请求总数
    private static int clientTotal = 5000;
    // 同时并发执行的线程总数
    private static int threadTotal = 200;

    private static Set<Integer> list = new CopyOnWriteArraySet<>();
    private static void update(int i){
        list.add(i);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0;i < clientTotal;i ++){
            final int j = i;
            executorService.execute( () ->{
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    log.error("exception:", e);
                }
                update(j);
                semaphore.release();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size: {}", list.size());
    }

}

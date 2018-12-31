package com.imooc.concurrency.example.commonUnsafe;

import com.imooc.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class DateTimeFormatterTest {

    // 请求总数
    private static int clientTotal = 5000;
    // 同时并发执行的线程总数
    private static int threadTotal = 200;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
    private static void update(final int i){
        log.info("{}, {}", i, DateTime.parse("2018-11-01", dateTimeFormatter).toDate());
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0;i < clientTotal;i ++){
            final int count = i;
            executorService.execute( () ->{
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    log.error("exception:", e);
                }
                update(count);
                semaphore.release();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

}

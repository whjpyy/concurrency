package com.imooc.concurrency.example.atomic;

import com.imooc.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicIntegerFieldUpdaterTest {

    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterTest> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterTest.class, "count");

    public volatile int count = 100;

    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterTest test = new AtomicIntegerFieldUpdaterTest();
        if(updater.compareAndSet(test, 100, 120)){
            log.info("udpate success1, {}", test.count);
        }

        if(updater.compareAndSet(test, 100, 120)){
            log.info("udpate success1, {}", test.count);
        }else{
            log.info("udpate failed, {}", test.count);
        }
    }
}

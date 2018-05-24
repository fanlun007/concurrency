package com.mmall.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class AtomicRef {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    @Test
    public void testCount(){
        count.compareAndSet(0, 1);  //1
        count.compareAndSet(0, 2);  //no
        count.compareAndSet(1, 2);  //2
        count.compareAndSet(2, 4);  //4
        count.compareAndSet(1, 7);  //no
        count.compareAndSet(4, 7);  //7

        log.info("count:{}", count);
    }

}

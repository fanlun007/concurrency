package com.mmall.completionService;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;


@Slf4j
class MyCallableA implements Callable<String> {
    @Override
    public String call() throws Exception {
        log.info("mycallableA begin " + System.currentTimeMillis());
        Thread.sleep(1000);
        log.info("mycallableB end" + System.currentTimeMillis());
        return "returnA";
    }
}

@Slf4j
class MycallableB implements Callable<String> {
    @Override
    public String call() throws Exception {
        log.info("mycallableB begin " + System.currentTimeMillis());
        Thread.sleep(5000);
        int i = 0;
        if (i ==0) {
            throw new Exception("抛出异常");
        }
        log.info("mycallableB end " + System.currentTimeMillis());
        return "returnB";
    }
}

@Slf4j
public class CompletionServiceTest {
    public static void main(String[] args){
        try {
            MyCallableA callableA = new MyCallableA();
            MycallableB mycallableB = new MycallableB();

            Executor executor = Executors.newCachedThreadPool();
            CompletionService completionService = new ExecutorCompletionService(executor);
            completionService.submit(callableA);
            completionService.submit(mycallableB);

            for (int i= 0; i<2; i++){
                log.info("----------" + completionService.take());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

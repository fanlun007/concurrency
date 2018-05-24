package com.mmall.concurrency;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class MyRunnable implements Runnable {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MyRunnable(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        try {
            System.out.println(username + " run");
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Run {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(2);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, queue, new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 5; i++) {
            MyRunnable runnable = new MyRunnable("Runnable" + (i + 1));
            executor.execute(runnable);
        }

        Thread.sleep(2000);

        Iterator iterator = queue.iterator();

        while (iterator.hasNext()) {
            Object object = iterator.next();
            System.out.println("-->" + ((MyRunnable) object).getUsername());
        }

        executor.execute(new MyRunnable(">Runnable 88 "));
        executor.execute(new MyRunnable(">Runnable 99 "));

        iterator = queue.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            System.out.println("-->" + ((MyRunnable) object).getUsername());
        }
    }


}

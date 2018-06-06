package ru.appavlov.learn.thread.lesson15;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Урок по Java 80_ Многопоточность 15_ Executors
 *
 * ограничение на количество потоков
 */
public class Start {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            executorService.submit(new MyRunnable());
            System.out.println(
                    executorService.submit(new MyCallable()).get()
            );
        }

        executorService.shutdown();
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("print MyRunnable");
            System.out.println(Thread.currentThread().getName());
        }
    }

    private static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName());
            return "Callable<String>";
        }
    }
}

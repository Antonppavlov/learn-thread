package ru.appavlov.learn.thread.lesson14;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Урок по Java 79_ Многопоточность 14_ Callable and Futures
 *
 * Если необходимо что-то вернуть в результате работы потока
 */
public class Start {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new MyCallable();
        FutureTask<String> stringFutureTask = new FutureTask<>(callable);
        new Thread(stringFutureTask).start();

        System.out.println(stringFutureTask.get());
    }

    private static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "Hello Callable and Futures!!!";
        }
    }
}

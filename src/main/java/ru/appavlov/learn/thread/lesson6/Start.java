package ru.appavlov.learn.thread.lesson6;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Урок по Java 71_ Многопоточность 6_ атомарные переменные
 */
public class Start {

    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10_000; i++) {
            new MyThread().start();
        }
        System.out.println(atomicInteger);
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        Start.atomicInteger.incrementAndGet();
    }
}

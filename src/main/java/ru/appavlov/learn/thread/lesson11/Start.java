package ru.appavlov.learn.thread.lesson11;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Урок по Java 76_ Многопоточность 11_ ReentrantLock
 */
public class Start {

    public static void main(String[] args) throws InterruptedException {
        new MyThread().start();
        new MyThread().start();
        new MyThread().start();


        Thread.sleep(100);
        System.out.println(Resources.i);
        System.out.println(Resources.j);
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        Resources.changeI();
        Resources.changeJ();
    }
}

class Resources {
    public static int i;
    public static int j;

    /**
     * работает как synchronized
     * но добавляет гибкости и возможности использования между методами
     */
    static Lock lock = new ReentrantLock();

    public static void changeI() {
        lock.lock();
        i++;
    }

    public static void changeJ() {
        j++;
        lock.unlock();
    }
}

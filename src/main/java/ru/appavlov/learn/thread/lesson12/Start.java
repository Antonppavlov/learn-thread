package ru.appavlov.learn.thread.lesson12;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Урок по Java 77_ Многопоточность 12_ TryLock
 */
public class Start {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new MyThread1().start();
        new MyThread2().start();
    }

    static class MyThread1 extends Thread {
        @Override
        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " start working");
            try {
                sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " stop working");
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " lock is released");

        }
    }

    static class MyThread2 extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start working");
            while (true) {
                if (lock.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + " start working");
                    System.out.println(Thread.currentThread().getName() + " stop working");
                    break;
                } else {
                    System.out.println(Thread.currentThread().getName() + " lock");
                }
            }
        }
    }
}

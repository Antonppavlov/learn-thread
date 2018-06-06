package ru.appavlov.learn.thread.lesson13;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Урок по Java 78_ Многопоточность 13_ Conditions
 */
public class Start {
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static int account;

    public static void main(String[] args) {
        new ThreadMinus().start();
        new ThreadPlus().start();

    }

    static class ThreadPlus extends Thread {
        @Override
        public void run() {
            lock.lock();
            account += 10;
            condition.signal();
            lock.unlock();
        }
    }

    static class ThreadMinus extends Thread {
        @Override
        public void run() {
            if (account < 10) {
                try {
                    lock.lock();
                    System.out.println("await because account account<10");
                    condition.await();
                    System.out.println("condition signal and account=" + account);
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            account -= 10;

            System.out.println("account= " + account);
        }
    }
}

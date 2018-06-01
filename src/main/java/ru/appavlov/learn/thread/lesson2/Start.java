package ru.appavlov.learn.thread.lesson2;

import static java.lang.Thread.sleep;

/**
 * Урок по Java 67_ Многопоточность 2_ жизненый цикл потоков
 */
public class Start {

    public static void main(String[] args) throws InterruptedException {
        new MyThread().start();
        /**
         *чтобы главный поток уснул и быстрее выполнился Thread-0
         */
        sleep(1000);
        System.out.println(Thread.currentThread().getName());

        new MyThread().start();
        /**
         *статический метод
         * вызывается в потоке для того чтобы самому стать в очередь и отдать время другому потоку
         */
        Thread.yield();
        System.out.println(Thread.currentThread().getName());

        /**
         *Хоть и запускается в отдельном потоке
         * но вызван метод join на потоке
         * потому другие потоки не будут запущенны пока не выполнится этот
         */
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
        );
        thread.start();
        thread.join();


        System.out.println(Thread.currentThread().getName());
    }

}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

}


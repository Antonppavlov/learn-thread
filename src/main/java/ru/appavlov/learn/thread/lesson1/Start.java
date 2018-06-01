package ru.appavlov.learn.thread.lesson1;
/**
 *Урок по Java 66_ Многопоточность 1_ Создание потоков
 */
public class Start {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        new MyThread().start();
        new MyThread().start();
        new MyThread().start();
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

        MyRunnable myRunnable = new MyRunnable();
        myRunnable.run();
        new Thread(myRunnable).start();

        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        new Thread(runnable).start();
    }

}

class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        throw new RuntimeException();
    }

}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}


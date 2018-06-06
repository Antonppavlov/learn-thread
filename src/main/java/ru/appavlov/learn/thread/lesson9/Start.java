package ru.appavlov.learn.thread.lesson9;

/**
 * Урок по Java 74_ Многопоточность 9_ Wait and notify
 */
public class Start {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();


        synchronized (myThread) {
            myThread.wait();
        }
        System.out.println(myThread.total);
    }
}

class MyThread extends Thread {
    int total;

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                total++;
                System.out.println(total);
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            notify();
        }
    }
}

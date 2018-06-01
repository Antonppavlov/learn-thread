package ru.appavlov.learn.thread.lesson5;

/**
 * Урок по Java 70_ Многопоточность 5_ volatile
 */
public class Start {
    /**
     * volatile - указывает чтобы потоки не кешировали в себе переменную
     * каждый раз будут получать ее при обращении
     */
    public volatile static int i = 0;

    public static void main(String[] args) {
        new MyThreadWrite().start();
        new MyThreadReader().start();
    }
}

class MyThreadWrite extends Thread {
    @Override
    public void run() {
        while (Start.i < 5) {
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("increment i to: " + ++Start.i);
        }
    }
}

class MyThreadReader extends Thread {
    @Override
    public void run() {
        int localVar = Start.i;
        while (localVar < 5) {
            if (localVar != Start.i) {
                System.out.println("new value i: " + Start.i);
                localVar = Start.i;
            }
        }
    }
}

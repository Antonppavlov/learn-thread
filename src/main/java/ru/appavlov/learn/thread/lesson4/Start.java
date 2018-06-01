package ru.appavlov.learn.thread.lesson4;

/**
 * Урок по Java 69_ Многопоточность 4_ синхронизация статический методов
 *
 * нельзя совмещать не с сатической
 */
public class Start {

    public static void main(String[] args) throws InterruptedException {
        Resources.i = 5;

        MyThread myThread0 = new MyThread();
        myThread0.start();

        MyThread myThread1 = new MyThread();
        myThread1.start();

        MyThread myThread2 = new MyThread();
        myThread2.start();

        MyThread myThread3 = new MyThread();
        myThread3.start();
        /**
         *чтобы успели выполнится остальные потоки по изменению i
         */
        Thread.sleep(100);
        System.out.println(Resources.i);
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        Resources.changeI();
    }
}

class Resources {
    public static int i;
    /**
     * т.к. можество потоков работает с ресурсом
     * может произойти одновременое изменение i от ее текущего состояния
     * если используется synchronized этого не произойдет
     * т.к с методом может одновременно работать только 1 поток
     * остальные будут ожидать
     * может быть только на уровне класса
     */
    public synchronized static void changeI() {
        /**
         * может быть только на уровне класса
         */
        synchronized (Resources.class) {
            i++;
        }
    }
}
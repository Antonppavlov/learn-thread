package ru.appavlov.learn.thread.lesson3;
/**
 *Урок по Java 68_ Многопоточность 3_ Синхронизация
 */
public class Start {

    public static void main(String[] args) throws InterruptedException {
        Resources resources = new Resources();
        resources.setI(5);

        MyThread myThread0 = new MyThread();
        myThread0.setResources(resources);
        myThread0.start();

        MyThread myThread1 = new MyThread();
        myThread1.setResources(resources);
        myThread1.start();

        MyThread myThread2 = new MyThread();
        myThread2.setResources(resources);
        myThread2.start();

        MyThread myThread3 = new MyThread();
        myThread3.setResources(resources);
        myThread3.start();
        /**
         *чтобы успели выполнится остальные потоки по изменению i
         */
        Thread.sleep(100);
        System.out.println(resources.getI());
    }
}

class MyThread extends Thread {
    private Resources resources;

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        resources.changeI();
    }
}

class Resources {
    private int i;

    public int getI() {
        return i;
    }

    public synchronized void setI(int i) {
        this.i = i;
    }

    /**
     * т.к. можество потоков работает с ресурсом
     * может произойти одновременое изменение i от ее текущего состояния
     * если используется synchronized этого не произойдет
     * т.к с методом может одновременно работать только 1 поток
     * остальные будут ожидать
     */
    public
    synchronized void changeI() {
        /**
         *можно также синхронизировать блок кода
         */
        synchronized (this) {
            i++;
        }

    }
}
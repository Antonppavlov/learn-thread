package ru.appavlov.learn.thread.lesson8;

/**
 * Урок по Java 73_ Многопоточность 8_ Deadlock
 */
public class Start {
    public static void main(String[] args) {
        ResourcesA resourcesA = new ResourcesA();
        ResourcesB resourcesB = new ResourcesB();
        resourcesA.resourcesB = resourcesB;
        resourcesB.resourcesA = resourcesA;

        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();

        myThread1.resourcesA = resourcesA;
        myThread2.resourcesB = resourcesB;

        myThread1.start();
        myThread2.start();
    }
}

class MyThread1 extends Thread {
    ResourcesA resourcesA;

    @Override
    public void run() {
        System.out.println(resourcesA.getIResourcesB());
    }
}

class MyThread2 extends Thread {
    ResourcesB resourcesB;

    @Override
    public void run() {
        System.out.println(resourcesB.getIResourcesA());
    }
}


class ResourcesA {
    ResourcesB resourcesB;

    synchronized int getIResourcesB() {
        return resourcesB.getI();
    }

    synchronized int getI() {
        return 1;
    }
}

class ResourcesB {
    ResourcesA resourcesA;

    synchronized int getIResourcesA() {
        return resourcesA.getI();
    }

    synchronized int getI() {
        return 2;
    }
}
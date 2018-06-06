package ru.appavlov.learn.thread.lesson7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Урок по Java 72_ Многопоточность 7_ Синхронизированные коллекции
 */
public class Start {

    public static void main(String[] args) {
        NameList first = new NameList();
        first.add("First");

        class MyThread extends Thread {
            @Override
            public void run() {
                System.out.println(first.removeFirst());
            }
        }

        new MyThread().start();
        new MyThread().start();
    }


    static class NameList {

        private List<String> list = Collections.synchronizedList(new ArrayList<>());

        public synchronized void add(String name) {
            list.add(name);
        }

        /**
         * метод все равно нужно синхронизировать потому что ошибка может произойти после if
         */
        public synchronized String removeFirst() {
            if (list.size() > 0) {
                String name = Thread.currentThread().getName();
                System.out.println(name);
                if (name.equals("Thread-0")) {
                    Thread.yield();
                }
                return list.remove(0);
            }
            return null;
        }

    }
}

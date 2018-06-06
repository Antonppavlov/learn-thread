package ru.appavlov.learn.thread.lesson10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Урок по Java 75_ Многопоточность 10_ Wait and Notify пример
 * https://youtu.be/ns1imummWPw
 */
public class Start {

    static List<String> stringList = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        new Operator().start();
        new Machine().start();
    }

    static class Operator extends Thread {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                synchronized (stringList) {
                    stringList.add(scanner.nextLine());
                    stringList.notify();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Machine extends Thread {
        @Override
        public void run() {
            while (stringList.isEmpty()) {
                synchronized (stringList) {
                    try {
                        stringList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Print and delete: " + stringList.remove(0));
                }
            }
        }
    }
}

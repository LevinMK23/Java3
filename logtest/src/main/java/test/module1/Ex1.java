package test.module1;

import java.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedDeque;

import static java.lang.Thread.sleep;

public class Ex1 {

    public static void main(String[] args) {
        ConcurrentLinkedDeque<Integer> list = new ConcurrentLinkedDeque<>();
        Object mon = new Object();
        new Thread(()->{
                for (int i = 0; i < 30; i++) {
                    list.add(i);
                    System.out.println("add " + i);
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }).start();
        new Thread(()->{
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Iterator<Integer> it = list.iterator();
                while (it.hasNext()) {
                    System.out.print(it.next() + " ");
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }).start();

    }

}


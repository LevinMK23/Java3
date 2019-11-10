package thread;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.function.Function;

import static java.lang.Thread.sleep;

public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        LinkedList<Integer> list = new LinkedList<>();
        Object lock = new Object();
        for (int i = 0; i < 50; i++) {
            synchronized (lock) {
                list.add(50);
            }
        }
        new Thread(()->{
            Iterator<Integer> it = list.iterator();
            for (int i = 0; i < 30; i++) {
                synchronized (lock) {
                    System.out.println(i + " added");
                    try {
                        list.add(i);
                        sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        new Thread(()->{
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()){
                synchronized (lock) {
                    System.out.print(it.next() + " ");
                    try {
                        sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                //Object o = new Object();
                System.out.println(i);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        LinkedList<Object> l = new LinkedList<>();
        new Thread(()->{
            for (int i = 0; i < 1000000000; i++) {
                l.add(new Object());
                //System.out.print(i + " ");
            }
        }).start();
    }
}

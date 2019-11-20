package classwork;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentModification {
    static int cnt;
    public static void main(String[] args) throws InterruptedException {
        LinkedList<Integer> list = new LinkedList<>();
        cnt = 0;
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                while (true) {
                    lock.lock();
                    list.add(cnt++);
                    lock.unlock();
                    //System.out.println(Thread.currentThread() + " add 0");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        Thread.sleep(3000);
        new Thread(() -> {
                lock.lock();
                for (Integer i : list) {
                    System.out.println(i);
                }
                lock.unlock();
        }).start();
    }
}

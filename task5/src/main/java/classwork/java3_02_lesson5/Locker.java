package classwork.java3_02_lesson5;

import java.util.concurrent.locks.ReentrantLock;

public class Locker {

    static final ReentrantLock lock = new ReentrantLock();
    static int cnt = 0;

    public static void func() {
        lock.lock();
        cnt++;
        System.out.println("cnt = " + cnt);
        if (cnt > 10) {
            cnt = 0;
            lock.unlock();
            System.out.println("Hard calculation start");
            for (int i = 0; i < 1000000000; i++) {
                for (int j = 0; j < 100; j++) {
                    
                }
            }
            System.out.println("Hard calculation finish");
        } else {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        /*for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                while (true) {
                    func();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }*/
        int a = 0;
        for (int i = 0; i < 1_000_000_000; i++) {
            for (int j = 0; j < 100; j++) {
                a *= i + j;
            }
        }
        System.out.println(a);
    }
}

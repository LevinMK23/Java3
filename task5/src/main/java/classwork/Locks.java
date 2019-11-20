package classwork;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {

    static ReentrantLock lock;

    static class Runner extends Thread {

        int limit, requestCounter;

        public Runner() {
            limit = 10;
            requestCounter = 0;
        }

        public boolean allow(){
            requestCounter++;
            return requestCounter > limit;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                String name = Thread.currentThread().getName();
                System.out.println(name + " action 1");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " action 2");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " action 3");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (allow()) {
                    requestCounter = 0;
                    lock.unlock();
                    for (int i = 0; i < 100000000; i++) {

                    }
                    System.out.println(name + " end hard task");
                } else {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        lock = new ReentrantLock();
        Runner [] runs = new Runner[8];
        for (int i = 0; i < 8; i++) {
            runs[i] = new Runner();
            runs[i].start();
        }
        Random r = new Random();
        while(true) {
            runs[r.nextInt(8)].allow();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

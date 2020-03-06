package classwork.java3_02_lesson5;

import java.util.concurrent.locks.ReentrantLock;

public class SyncPrimitive2 {
    //locks
    private static int counter = 0;


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int count = Integer.parseInt(Thread
                        .currentThread()
                        .getName()
                        .replaceAll("Thread-", ""));
                while (true) {
                    if (count == counter) {
                        lock.lock();
                        for (int j = 0; j < 15; j++) {
                            System.out.println(Thread.currentThread().getName() + " " + j);
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        lock.unlock();
                        counter++;
                        break;
                    } else {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

    }
}

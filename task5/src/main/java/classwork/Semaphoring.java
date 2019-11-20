package classwork;

import java.util.concurrent.Semaphore;

public class Semaphoring {

    public static void main(String[] args) {
        Semaphore multiMutex = new Semaphore(3);
        for (int i = 0; i < 12; i++) {
            int finalI = i;
            new Thread(() -> {
                int cnt = finalI + 1;
                System.out.println("Thread " + cnt + " wait");
                try {
                    //Thread.sleep(1000);
                    multiMutex.acquire();
                    System.out.println("Thread " + cnt + " run");
                    Thread.sleep(1000);
                    System.out.println("Thread " + cnt + " finish");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    multiMutex.release();
                }
            }).start();
        }
    }
}

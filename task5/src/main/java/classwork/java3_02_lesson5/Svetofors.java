package classwork.java3_02_lesson5;

import java.util.concurrent.Semaphore;

public class Svetofors {
    public static void main(String[] args) {
        Semaphore sm = new Semaphore(4);

        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                try {
                    sm.acquire();
                    for (int j = 0; j < 5; j++) {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(500);
                    }
                    sm.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

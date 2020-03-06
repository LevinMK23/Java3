package classwork.java3_02_lesson5;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class SyncPrimitive1 {

    public static void main(String[] args) {
        //atomic classes
        //AtomicInteger counter = new AtomicInteger(0);
        // 1 sec -> limit
        // requests per seconds > limit
        RPM metric = new RPM();
        Random rnd = new Random();
        new Thread(()->{
            while(true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(metric.eventCount());
            }
        }).start();
        for (int i = 0; i < 100; i++) {
            System.out.println(metric.eventCount());
            new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    metric.addEvent();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        System.out.println(metric.eventCount());
    }
}

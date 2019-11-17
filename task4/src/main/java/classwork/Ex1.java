package classwork;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Ex1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Object mutex = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (mutex) {
                while (true) {
                    mutex.notify();
                    System.out.print("A ");
                    try {
                        Thread.sleep(500);
                        mutex.wait();
                        //mutex.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (mutex) {
                while (true) {
                    mutex.notify();
                    System.out.print("B ");
                    try {
                        Thread.sleep(500);
                        mutex.wait();
                        //mutex.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        ExecutorService service = Executors
                .newFixedThreadPool(8);
//        for (int i = 0; i < 8; i++) {
//            int finalI = i;
//            service.execute(()->{
//                    System.out.print((char)('A' + finalI));
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//            });
//        }
        Future<String> future = service.submit(() -> {
            for (long i = 0; i < 10000000000L; i++) {

            }
            return "The End";
        });
        System.out.println(future.get());
        service.shutdown();
    }
}

package classwork.java3_02_lesson5;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

public class RPM {

    private ConcurrentLinkedDeque<AtomicLong> queue;
    private int timeQ, qCount;

    public RPM() {
        timeQ = 100;
        qCount = 1000 / timeQ;
        queue = new ConcurrentLinkedDeque<>();
        queue.add(new AtomicLong());
        new Thread(()->{
            while (true) {
                try {
                    Thread.sleep(timeQ);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (queue.size() < qCount) {
                    queue.add(new AtomicLong());
                } else {
                    queue.pollFirst();
                    queue.add(new AtomicLong());
//                    System.out.println("Block added queue size = " + queue.size());
//                    for (AtomicLong al : queue) {
//                        System.out.println(al);
//                    }
                }
            }
        }).start();
    }

    public void addEvent() {
        queue.getLast().incrementAndGet();
    }

    synchronized public long eventCount() {
        long val = 0;
        for (AtomicLong al : queue) {
            val += al.get();
        }
        return val;
    }

}

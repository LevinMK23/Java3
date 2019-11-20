package classwork;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestsPerSecond {

    int limit;
    ConcurrentLinkedDeque<AtomicInteger> queue;

    public RequestsPerSecond(int limit) {
        this.limit = limit;
        queue = new ConcurrentLinkedDeque<>();
        for (int i = 0; i < 1000; i++) {
            queue.add(new AtomicInteger(0));
        }
        new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.pollFirst();
                queue.addLast(new AtomicInteger(0));
            }
        }).start();
    }

    boolean allow() {
        queue.getLast().incrementAndGet();
        if(getRequestsBySecond() > limit) return false;
        return true;
    }

    int getRequestsBySecond() {
        //System.out.println(queue.size());
        int sum = 0, cnt = 0;
        for (AtomicInteger i : queue) {
            sum += i.get();
            cnt++;
            //System.out.print(i.get() + " ");
        }
        return sum;
    }

    int getRequestsByMillis() {
        return queue.getLast().get();
    }
}

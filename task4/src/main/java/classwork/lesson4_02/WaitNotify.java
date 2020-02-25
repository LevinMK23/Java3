package classwork.lesson4_02;

public class WaitNotify {
    public static void main(String[] args) {
        // binary semaphore
        Object mutex = new Object();
        synchronized (mutex) {
            mutex.notify();
            try {
                System.out.println("try wait");
                mutex.wait();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted");
            }
        }
    }
}

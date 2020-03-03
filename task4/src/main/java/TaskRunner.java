import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskRunner implements Runnable {

    //TODO
    
    // по заданию из Гит не понял что именно нужно сделать...
    Object mutex;   //монитор синхронизации
    String message; //сообщение которое поток будет добавлять в список

    //у вас могут быть другие решения и другие переменные
    volatile int cnt;
    volatile int inc;
    static volatile int iter = 0;
    //этот список будет проверяться на предмет синхронизации
    //данных
    //а именно: [1, 2, 3, 1, 2, 3, 1, 2, 3....]
    //в список будут писать из 3 потоков
    static volatile LinkedList<String> list = new LinkedList<>();


    
    public TaskRunner(Object mutex, String msg, int cnt) {
        this.mutex = mutex;
        message = msg; //каждый поток может писать в список только
        //его сообщение!!!
        //System.out.println(message);
        this.cnt = cnt;
        inc = 0;
    }

    @Override
    public void run() {
        // TODO: 26.12.2019
        while (list.size() < 60) {
            synchronized (mutex) {
                if ((iter % 30) == cnt) {
                    list.add(message);
                    iter++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println(list);
    }

    public void threeThreads() { // Задача с сайта через notify не удалось...буду штутдировать дальше
        Thread A = new Thread(()-> {
                System.out.print("A");
        });
        Thread B = new Thread(()-> {
                System.out.print("B");
        });
        Thread C = new Thread(()-> {
                System.out.print("C");
        });
        A.start();
        B.start();
        C.start();
        try {
            A.join();
            B.join();
            C.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


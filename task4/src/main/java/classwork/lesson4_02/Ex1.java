package classwork.lesson4_02;

import java.util.ArrayList;

public class Ex1 {
    // cap

    // консистентность - доверительное свойство данных
    // методы достижения:
    // N потоков и 1 ресурс
    // определено: порядок выполнения, атомарность операций



    static volatile int counter = 0; // a = 5

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> list = new ArrayList<>();
        new Thread(() -> {
            if (counter == 0) {
                System.out.println("Thread1 run");
                synchronized (list) {
                    for (int i = 0; i < 15; i++) {
                        list.add(i + 1);
                    }
                    counter = 1;
                    list.notify();
                }
                System.out.println("Thread1 unlock list");
            }
        }).start();
        new Thread(() -> {
            while (counter != 1) {
                synchronized (list) {
                    try {
                        System.out.println("Thread2 wait");
                        list.notify();
                        list.wait();
                        //no code
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Thread2 run");
            if (counter == 1) {
                synchronized (list) {
                    //System.out.println("\nThread2");
                    for (int i = 0; i < 15; i++) {
                        list.add(i + 1);
                    }
                    counter = 2;
                    list.notify();
                }
                System.out.println("Thread2 unlock list");
            }
        }).start();
        new Thread(() -> {
            while (counter != 2) {
                synchronized (list) {
                    try {
                        System.out.println("Thread3 wait");
                        list.notify();
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Thread3 run");
            synchronized (list) {
                for (Integer integer : list) {
                    System.out.print(integer + " ");
                }
                list.notifyAll();
            }
        }).start();
    }
}

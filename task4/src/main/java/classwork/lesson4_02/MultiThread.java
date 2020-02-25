package classwork.lesson4_02;

import java.util.Arrays;
import java.util.List;

public class MultiThread {

    static void execute(List<Runnable> tasks) {
        // TODO: 25.02.2020
        for (Runnable task : tasks) {
            new Thread(task).start();
        }
    }

    static class Runner implements Runnable {

        private String msg;

        public Runner(String msg) {
            this.msg = msg;
        }

        // TODO: 25.02.2020

        @Override
        public void run() {
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        Runnable [] tasks = new Runnable[30];
        for (int i = 0; i < 30; i++) {
            tasks[i] = new Runner(String.valueOf(i+1));
        }
        execute(Arrays.asList(tasks));
    }

}

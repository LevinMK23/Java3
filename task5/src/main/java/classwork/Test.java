package classwork;

import java.util.Random;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        RequestsPerSecond rps = new RequestsPerSecond(1000);
        Random r = new Random();
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            String msg = in.next();
//            if(msg.equals("how")) System.out.println(rps.getRequestsBySecond());
//            rps.allow();
//        }
        for (int i = 0; i < 100; i++) {
            new Thread(()-> {
                while (true) {
                    rps.allow();
                    //System.out.println("doing something");
                    try {
                        Thread.sleep(r.nextInt(150));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(1500);
                    System.out.println(rps.getRequestsByMillis());
                    System.out.println(rps.getRequestsBySecond());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

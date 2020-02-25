package classwork.lesson4_02;

import java.util.concurrent.*;

public class ExecutorTest {
    public static void main(String[] args) {
        ExecutorService service = Executors
                .newFixedThreadPool(4);
        // user1, user2, user3
        // user1 -> user2 task -> service update (user1, user2) ->
        // connections (time)
        // user1 -> all users (12 users) -> task subscribe
        // threads4 (by 4)
        // vk message -> user queue(data) data[7].destination -> user
        // ассоциативные задачи [1 2 3 4 5 6 = 6 5 4 3 2 1] = 1 6 2 5 4 3
        // todo сумма ряда в несколько потоков
        for (int i = 0; i < 150; i++) {
            int finalI = i;
            Callable<String> callback = () -> {
                System.out.println(finalI);
                return "OK " + finalI;
            };
            Future<String> future = service.submit(callback);
            new Thread(() -> {
                    try {
                        System.out.println(future.get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }).start();
        }
        service.shutdown();
    }
}

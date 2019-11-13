import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import java.util.LinkedList;

@DisplayName("Homework 4")
public class ThreadTest {

    ThreadTask task;
    Object mon;

    @Before
    public void starter(){
        task = new ThreadTask();
        mon = new Object();
    }

    @Test
    public void checkList(){
        //тест длится 30 секунд и запускается 3 раза
        //чтобы хоть немножко исключить везение)))
        for (int j = 0; j < 3; j++) {
            TaskRunner [] runners = new TaskRunner[3];
            Thread [] threads = new Thread[3];
            for (int i = 0; i < 3; i++) {
                runners[i] = new TaskRunner(mon, String.valueOf(i+1), i);
                threads[i] = new Thread(runners[i]);
            }
            task.multiThreadRecord(threads);
            try {
                Thread.sleep(10000);
                int cnt = 0;
                int [] arr = new int[]{1,2,3};
                System.out.println(TaskRunner.list);
                for (int i = 0; i < 9; i++) {
                    Assert.assertEquals(String.valueOf(arr[cnt]), TaskRunner.list.get(i));
                    cnt = (cnt + 1) % 3;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

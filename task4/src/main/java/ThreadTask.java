import java.util.LinkedList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTask {

    // менять нельзя!
    public void multiThreadRecord(Thread [] threads){
        for (int i = 0; i < 3; i++) {
            threads[i].start();
        }
    }



}

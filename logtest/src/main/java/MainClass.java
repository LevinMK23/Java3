import java.io.IOException;
import java.util.Arrays;
import java.util.logging.*;

public class MainClass {
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(MainClass.class.getName());
        logger.addHandler(new FileHandler("C:\\Users\\Mikhail\\IdeaProjects\\Java3\\logtest\\src\\main\\resources\\log.txt"));
        //logger.addHandler(new ConsoleHandler());
        //logger.setFilter(record -> record.getMessage().startsWith("He"));
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            StackTraceElement [] elements = Thread.currentThread().getStackTrace();
            logger.warning(e.toString() + "\n" + Arrays.toString(elements));
        }
    }
}

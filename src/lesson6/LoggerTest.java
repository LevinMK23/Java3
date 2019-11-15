package lesson6;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggerTest {

    public static void main(String[] args) {
        Logger log = Logger.getLogger("");
//        log.getHandlers()[0].setFormatter(new Formatter() {
//            @Override
//            public String format(LogRecord record) {
//                return record.getLevel() + " " + record.getMessage() + "\n";
//            }
//        });
        log.warning("asfasf");
        log.info("LOL");
    }
}

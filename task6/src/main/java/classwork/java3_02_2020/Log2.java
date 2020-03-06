package classwork.java3_02_2020;

import org.apache.log4j.Logger;

import java.io.IOException;

public class Log2 {
    private final static Logger log = Logger.getLogger(Log2.class);

    static int foo() {
        return foo();
    }

    public static void main(String[] args) {
        try {
            foo();
        }catch (StackOverflowError e) {
            log.error("e=", e);
        }

    }
}

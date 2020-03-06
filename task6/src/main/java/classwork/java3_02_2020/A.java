package classwork.java3_02_2020;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class A {

    private final static Logger log = Logger.getLogger(A.class);

    void hardFunc(int n) {
        while (n-- > 0) {
            log.debug("" + n);
        }
    }

    void foo(int a) {
        log.debug("executed with a = " + a);
        System.out.println(a);
    }

    void bar(int a, int b) {
        log.info("executed with a = " + a + ", b = " + b);
    }
}

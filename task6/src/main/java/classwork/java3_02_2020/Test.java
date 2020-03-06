package classwork.java3_02_2020;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class Test {
    private final static Logger log = Logger.getLogger(Test.class);


    public static void main(String[] args) throws IOException {
        A a = new A();
        a.hardFunc(10);
        a.foo(12);
        a.bar(1, 2);
        new B().bar(5, 6);
    }
}

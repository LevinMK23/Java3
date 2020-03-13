package classWork;

import java.util.*;
import java.util.stream.IntStream;


public class Ex2 {

    private static class A {
        int a, b, c;

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof A)) {
                return false;
            }
            return a + b == ((A) obj).a + ((A) obj).b;
        }

    }

    public static void main(String[] args) {
        RuntimeException e = new RuntimeException("LOL");
        try {
            throw e;
        } catch (Exception exc) {
           throw  e;
        }

    }
}

package classwork.java3_02_2020;

public class Util {
    public static int sum(int a, int b) {
        return a + b;
    }

    public static int[] arrayGen(int len) {
        return new int[len];
    }
    public static Throwable genException() {
        throw new ArithmeticException();
    }

    public static void longFoo() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

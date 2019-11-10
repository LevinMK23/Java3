package test;


import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(new File("test.in"));
        while (in.hasNextChar()){
            System.out.println(in.nextLine());
        }
    }
}

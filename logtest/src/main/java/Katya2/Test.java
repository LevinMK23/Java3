package Katya2;

import test.FastScanner;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println(tailFib(10, 1, 1, 0));
        //f(100);
        System.out.println(f(1500) + " " + cnt);
    }

    static int tailFib(int num, int iter, int f1, int f2){
        if(num == iter) return f1;
        return tailFib(num, iter+1, f1 + f2, f1);

    }

    static int cnt = 0;

    static int f(int n){
        cnt++;
        return n <= 1 ? 1 : f(n / 2) + f(n / 2);
    }
}

package test.module1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), t, zeroes = 0;
        for (int i = 0; i < n; i++) {
            t = in.nextInt();
            if(t != 0) System.out.print(t + " ");
            else zeroes++;
        }
        for (int i = 0; i < zeroes; i++) {
            System.out.print(0 + " ");
        }
    }
}

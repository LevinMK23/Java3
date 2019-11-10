package div3_595;

import java.util.Arrays;
import java.util.Scanner;

public class TaskA {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        while (q-- > 0){
            int n = in.nextInt();
            if(n > 2){
                int [] a = new int[n];
                for (int i = 0; i < n; i++) {
                    a[i] = in.nextInt();
                }
                Arrays.sort(a);
                boolean f = true;
                for (int i = 0; i < n - 1; i++) {
                    if(Math.abs(a[i] - a[i+1]) < 2){
                        System.out.println(2);
                        f = false;
                        break;
                    }
                }
                if(f) System.out.println(1);
            }
            else if(n == 2){
                int a = in.nextInt(), b = in.nextInt();
                if(Math.abs(a - b) > 1){
                    System.out.println(1);
                }
                else System.out.println(2);
            }
            else {
                int a = in.nextInt();
                System.out.println(1);
            }
        }
    }
}

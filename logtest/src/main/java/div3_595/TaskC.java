package div3_595;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TaskC {


    static long sum(int start, long sum, long n, boolean [] vis, long [] pow){
        if(start > pow.length-1){
            return Long.MAX_VALUE;
        }
        if(vis[start]){
            return Long.MAX_VALUE;
        }
        if(sum >= n) return sum;
        vis[start] = true;
        return Math.min(sum(start, sum + pow[start], n, vis, pow), sum(start + 1, sum, n, vis, pow));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean [] vis = new boolean[100];
        long [] pow = new long[100];
        pow[0] = 1;
        for (int i = 0; i < 99; i++) {
            pow[i+1] = pow[i] * 3;
        }
        System.out.println(sum(0, 0, 5, vis, pow));
//        int q = in.nextInt();
//        while (q-- > 0){
//            long n = in.nextLong();
//            long i = 0, p = 1, tmp = 0;
//            if(n == 1){
//                System.out.println(1);
//                continue;
//            }
//            while (i < n){
//                i += p;
//                p *= 3;
//                if(i == n){
//                    break;
//                }
//            }
//            p = 1;
//            long j = i;
//            while (j > n){
//                j -= p;
//                p *= 3;
//                if(j == n){
//                    break;
//                }
//            }
//            j += p / 3;
//            System.out.println(Math.min(i, j));
//        }
    }
}

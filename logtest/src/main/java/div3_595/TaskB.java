package div3_595;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskB {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        while (q-- > 0){
            int n = in.nextInt();
            int [] a = new int[n+1];
            for (int i = 1; i <= n; i++) {
                a[i] = in.nextInt();
            }
            boolean [] used = new boolean[n+1];
            int [] ans = new int[n+1];
            for (int i = 1; i <= n; i++) {
                if(!used[i]) {
                    ArrayList<Integer> cur = new ArrayList<>();
                    while (!used[i]) {
                        used[i] = true;
                        cur.add(i);
                        i = a[i];
                    }
                    for(Integer item : cur) ans[item] = cur.size();
                }
            }
            for (int i = 1; i <= n; i++) {
                System.out.print(ans[i] + " ");
            }
            System.out.println();
        }
    }
}

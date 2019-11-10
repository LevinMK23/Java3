package div3_595;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskF {

    static long sum = 0;

    static void dfs(ArrayList<ArrayList<Integer>> g, int [] w, boolean [] used, int v, int d, int dis){
        if(!used[v]){
            used[v] = true;
            if(d % dis == 0){
                sum += w[v];
                for (Integer i : g.get(v)){
                    dfs(g, w, used, i, 1, dis);
                }
            }
            //System.out.println(v + " : " + d);
            for (Integer i : g.get(v)){
                dfs(g, w, used, i, d+1, dis);
            }

        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), d = in.nextInt();
        int [] w = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            w[i] = in.nextInt();
        }
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int f = in.nextInt(), t = in.nextInt();
            graph.get(f).add(t);
            graph.get(t).add(f);
        }
        long max = 0;
        for (int i = 1; i < n+1; i++) {
            boolean [] used = new boolean[n + 1];
            dfs(graph, w, used, i, d, d);
            //System.out.println();
            max = Math.max(sum, max);
            sum = 0;
        }
        System.out.println(max);
    }
}

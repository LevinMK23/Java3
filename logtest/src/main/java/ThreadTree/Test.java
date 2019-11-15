package ThreadTree;

import java.util.Random;
import java.util.TreeSet;

public class Test {

    static Node t1, t2;

    static void add(Node n, int value){
        if(n != null){
            if(value > n.val) {
                if(n.r == null){
                    n.r = new Node(value);
                }
                else add(n.r, value);
            }
            if(value < n.val) {
                if(n.l == null){
                    n.l = new Node(value);
                }
                else add(n.l, value);
            }
        }

    }

    static void show(Node n){
        if(n != null){
            show(n.r);
            if(n.r == null && n.l == null) set.add(n.val);
            //System.out.print(n.val + " ");
            show(n.l);
        }
    }

    static void show1(Node n){
        if(n != null){
            System.out.print(n.val + " ");
            show1(n.l);
            show1(n.r);
        }
    }

    static TreeSet<Integer> set;
    public static void main(String[] args) {
        Random rnd = new Random();
        set = new TreeSet<>((x1, x2)-> x2 - x1);
        t1 = new Node(2);
        t2 = new Node(5);
        for (int i = 1; i < 70; i++) {
            add(t1, rnd.nextInt(100));
            add(t2, rnd.nextInt(100));
        }
        show1(t1);
        System.out.println();
        show1(t2);
        System.out.println();
        show(t1);
        show(t2);
        System.out.println(set);
    }
}


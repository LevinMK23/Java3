package ThreadTree;

public class Runner implements Runnable{

    Object mon;
    RunnerNot other;
    Node node;
    int tmp;

    synchronized void in(Node n){

            if (n == null) return;
            in(n.r);
            if (n.l == null && n.r == null) {
                tmp = n.val;
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.print(Math.max(tmp, other.tmp) + " " + Math.min(tmp, other.tmp) + " ");
            }
            in(n.l);

    }

    @Override
    public void run() {
        in(node);
    }
}

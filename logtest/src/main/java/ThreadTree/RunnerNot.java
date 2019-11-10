package ThreadTree;

public class RunnerNot implements Runnable{
    Object mon;
    Runner runner;
    Node node;
    int tmp;

    void in(Node n) {
            synchronized (runner) {
                if (n == null) return;
                in(n.r);
                if (n.l == null && n.r == null) {
                    tmp = n.val;
                    notify();
                }
                in(n.l);
            }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        in(node);
    }
}

package homework;

public class Test {

    FuncTools inst;
    @Before
    public void init() {
        inst = new FuncTools();
    }

    @TestCase(priority = 1)
    public void test1() {
        int expected = 65;
        int actually = inst.sum(3,3);
        if (expected != actually) {
            throw new RuntimeException("assertion error in test1");
        }
    }

    @TestCase(priority = 12)
    public void test2() {
        int expected = 9;
        int actually = inst.mul(3,3);
        if (expected != actually) {
            throw new RuntimeException("assertion error in test2");
        }
    }

    @TestCase(priority = 4)
    public void test3() {
        int expected = 1;
        int actually = inst.div(3,3);
        if (expected != actually) {
            throw new RuntimeException("assertion error in test3");
        }
    }

    @After
    public void finish() {
        System.out.println("Test passed");
    }
}

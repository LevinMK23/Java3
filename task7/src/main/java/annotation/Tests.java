package annotation;

public class Tests {

    Utility util;

    @Before
    public void starter(){
        util = new Utility(25, 13);
    }

    @Test(priority = 1)
    public void sumTest(){
        assert util.sum() == 38;
    }

    @Test(priority = 1)
    public void subTest(){
        assert util.sub() == 12;
    }

    @Test(priority = 3)
    public void mulTest(){
        assert util.mul() == (25 * 13);
    }

    @Test(priority = 3)
    public void divTest(){
        assert util.div() == (25 / 13);
    }

    @Test(priority = 3)
    public void modTest(){
        assert util.mod() == (25 % 13);
    }

    @After
    public void end() {
        System.err.println("All tests passed!");
    }
}

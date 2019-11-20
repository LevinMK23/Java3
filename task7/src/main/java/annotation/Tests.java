package annotation;

public class Tests {

    Utility util;

    @Before
    public void starter(){
        util = new Utility(25, 13);
    }

    @Test(priority = 1)
    public void sumTest(){
        // TODO: 18/11/2019  
    }

    @Test(priority = 1)
    public void subTest(){
        // TODO: 18/11/2019  
    }

    @Test(priority = 3)
    public void mulTest(){
        // TODO: 18/11/2019  
    }

    @Test(priority = 3)
    public void divTest(){
        // TODO: 18/11/2019  
    }

    @Test(priority = 3)
    public void modTest(){
        // TODO: 18/11/2019  
    }

    @After
    public void end() {
        System.err.println("All tests passed!");
    }
}

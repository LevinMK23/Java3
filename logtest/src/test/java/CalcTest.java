import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalcTest {

    Calc calc;

    @Before
    public void init(){
        calc = new Calc();
    }

    @Test
    public void sum() {
        //Assert.assertEquals(9, calc.sum(3,6));
    }

    @Test
    public void subtract() {
        Assert.assertEquals(-3, calc.subtract(3,6));
    }

    @Test
    public void multiply() {
        Assert.assertEquals(18, calc.multiply(3,6));
    }

    @Test(expected = ArithmeticException.class)
    public void divByZero(){
        calc.division(5, 0);
    }

    @Test(timeout = 1000L)
    public void timeTest(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void arrayTest(){
        Assert.assertArrayEquals(new int[]{1,2,3},
                new int[]{1,2,3,4});
    }
}
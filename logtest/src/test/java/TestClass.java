import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

@RunWith(Parameterized.class)
public class TestClass {

    @Parameterized.Parameters
    public static Long [][] func(){
        Random rnd = new Random();
        Long [][] array = new Long[20][3];
        for (int i = 0; i < 20; i++) {
            array[i][0] = rnd.nextLong();
            array[i][1] = rnd.nextLong();
            array[i][2] = array[i][0] + array[i][1];
        }
        return array;
    }

    long a, b, c;
    Calc calc;


    public TestClass(long a, long b, long c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Before
    public void init(){
        calc = new Calc();
    }

    @Test
    public void foo1(){
       // Assert.assertEquals(c, calc.sum(a, b));
    }
}

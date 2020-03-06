package java3_02_2020;

import classwork.java3_02_2020.Util;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Tests {

    public int a, b, c;

    @Parameterized.Parameters
    public static Integer[][] data() {
        return new Integer[][]{
                {1, 2, 3},
                {2, 2, 4},
                {5, 5, 10},
                {1, 1, 2},
                {0, 0, 0}
        };
    }

    public Tests(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
//    @Before
//    public void start() {
//        System.out.println("method start invoked");
//        System.out.printf("a=%d b=%d c=%d\n",a,b,c);
//
//    }

//    @After
//    public void finish() {
//        a++; b++; c += 2;
//        System.out.println("method finish invoked");
//        System.out.printf("a=%d b=%d c=%d\n",a,b,c);
//    }

    @Test
    public void testSum() {
        Assert.assertEquals(c, Util.sum(a, b));
    }

    @Test
    public void testSum1() {
        Assert.assertEquals(c, Util.sum(a, b));
    }

    @Test
    public void testArray() {
        Assert.assertArrayEquals(new int[c], Util.arrayGen(c));
    }

    @Test(expected = ArithmeticException.class)
    public void testException() {
        Util.genException();
    }

    @Test(timeout = 1000)
    public void stressTest() {
        Util.longFoo();
    }
}

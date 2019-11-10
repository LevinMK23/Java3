import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestClass {
    Box<Orange> b1, b3;
    Box<Apple> b2;

    @Before
    public void init(){
        b1 = new Box<>();
        b2 = new Box<>();
        b3 = new Box<>();
        for (int i = 0; i < 10; i++) {
            b1.addFruit(new Orange());
        }
        for (int i = 0; i < 10; i++) {
            b2.addFruit(new Apple());
        }
        for (int i = 0; i < 10; i++) {
            b3.addFruit(new Orange());
        }
    }

    @Test
    public void testCompare(){
        //Assert.assertFalse(b1.compareTo(b2));
        Assert.assertTrue(new Box<Orange>()
                .compareTo(new Box<Orange>()));
        Assert.assertTrue(b1.compareTo(b3));
    }

    @Test
    public void testDrop(){
        b1.dropFruits(b3);
        Assert.assertEquals(30., b3.getWeight(), 0.0001);
        Assert.assertEquals(0., b1.getWeight(), 0.0001);
    }
}

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Array;
import java.util.*;

class TestData {
    public List<Integer> inputArray;
    public List<Integer> expectedArray;

    TestData(List<Integer> inputArray, List<Integer> expectedArray) {
        this.inputArray = inputArray;
        this.expectedArray = expectedArray;
    }
}

@RunWith(Parameterized.class)
public class Tests {

    @Parameterized.Parameters
    public static TestData[] data() {
        final int NUM_TESTS = 100;
        TestData[] testData = new TestData[NUM_TESTS];
        Random random = new Random();

        for (int i = 0; i < NUM_TESTS; i++) {
            List<Integer> arr = new ArrayList<>();

            for (int j = 0; j < 1000; j++) {
                arr.add(random.nextInt(1000));
            }

            List<Integer> sortedArr = new ArrayList<>(arr);
            sortedArr.sort(Comparator.naturalOrder());

            testData[i] = new TestData(arr, sortedArr);
        }
        return testData;
    }

    private ForTests forTests;
    private List<Integer> inputArray;
    private List<Integer> expectedArray;

    public Tests(TestData data) {
        this.inputArray = data.inputArray;
        this.expectedArray = data.expectedArray;
        forTests = new ForTests();
    }

    @Test
    public void sortTest() {
        Assert.assertArrayEquals(expectedArray.toArray(), forTests.sort(inputArray).toArray());
    }

    @Test
    public void binarySearchTest() {
        int[] inputArr = {1, 2, 3, 5, 6, 7};

        Assert.assertEquals(0, forTests.binarySearch(inputArr, 1, 0, inputArr.length));
        Assert.assertEquals(1, forTests.binarySearch(inputArr, 2, 0, inputArr.length));
        Assert.assertEquals(2, forTests.binarySearch(inputArr, 3, 0, inputArr.length));
        Assert.assertEquals(3, forTests.binarySearch(inputArr, 5, 0, inputArr.length));
        Assert.assertEquals(4, forTests.binarySearch(inputArr, 6, 0, inputArr.length));
        Assert.assertEquals(5, forTests.binarySearch(inputArr, 7, 0, inputArr.length));
        Assert.assertEquals(-1, forTests.binarySearch(inputArr, 0, 0, inputArr.length));
        Assert.assertEquals(-1, forTests.binarySearch(inputArr, 8, 0, inputArr.length));
        Assert.assertEquals(-1, forTests.binarySearch(inputArr, 4, 0, inputArr.length));

        inputArr = new int[]{};

        Assert.assertEquals(-1, forTests.binarySearch(inputArr, 1, 0, inputArr.length));
        Assert.assertEquals(-1, forTests.binarySearch(inputArr, 2, 0, inputArr.length));
        Assert.assertEquals(-1, forTests.binarySearch(inputArr, 3, 0, inputArr.length));
        Assert.assertEquals(-1, forTests.binarySearch(inputArr, 4, 0, inputArr.length));
        Assert.assertEquals(-1, forTests.binarySearch(inputArr, 5, 0, inputArr.length));
        Assert.assertEquals(-1, forTests.binarySearch(inputArr, 6, 0, inputArr.length));

        inputArr = new int[65536];
        for (int i = 0; i < 65536; i++) {
            inputArr[i] = i;
        }

        Assert.assertEquals(65535, forTests.binarySearch(inputArr, 65535, 0, inputArr.length));
    }

    @Test(expected = RuntimeException.class)
    public void arrAfter4ExceptionTest() {
        int[] in = {1, 2, 3};
        int[] arr = forTests.arrAfter4(in);


//        Assert.assertArrayEquals(out, forTests.arrAfter4(in));
//        Assert.assertArrayEquals(out, forTests.arrAfter4(in));
    }

    @Test
    public void arrAfter4Test() {
        int[] in1 = {1, 2, 3, 4, 5};
        int[] out1 = {5};
        int[] in2 = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] out2 = {1, 7};
        int[] in3 = {4};
        int[] out3 = {};

        Assert.assertArrayEquals(out1, forTests.arrAfter4(in1));
        Assert.assertArrayEquals(out2, forTests.arrAfter4(in2));
        Assert.assertArrayEquals(out3, forTests.arrAfter4(in3));
    }

    @Test
    public void arrOneAndFour() {
        int[] in1 = {1, 1, 1, 4, 4, 1, 4, 4};
        boolean out1 = true;
        int[] in2 = {1, 1, 1, 1, 1, 1};
        boolean out2 = false;
        int[] in3 = {4, 4, 4, 4};
        boolean out3 = false;
        int[] in4 = {1, 4, 4, 1, 1, 4, 3};
        boolean out4 = false;
        int[] in5 = {};
        boolean out5 = false;

        Assert.assertEquals(out1, forTests.arrHasOneOrFour(in1));
        Assert.assertEquals(out2, forTests.arrHasOneOrFour(in2));
        Assert.assertEquals(out3, forTests.arrHasOneOrFour(in3));
        Assert.assertEquals(out4, forTests.arrHasOneOrFour(in4));
        Assert.assertEquals(out5, forTests.arrHasOneOrFour(in5));
    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

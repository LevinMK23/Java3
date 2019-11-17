import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@DisplayName("Home work 3")
public class FileTests {

    FileUtility util;
    String pathToResource;

    @Before
    public void starter(){
        //change your path
        pathToResource = "C:\\Users\\Mikhail\\IdeaProjects\\Java3\\task3\\src\\main\\resources\\";
        util = new FileUtility();
    }

    @Test
    public void testSort(){
        File input = new File(pathToResource + "array.txt");
        File output = new File(pathToResource + "arrayout.txt");
        util.sortEvenElements(input, output);
        int [] arr = new int[]{4, 8, 5, 7, 10, 9, 7, 12, 11, 14};
        try {
            Scanner in = new Scanner(output);
            int pos = 0;
            while (in.hasNext()){
                int x = in.nextInt();
                Assert.assertEquals(arr[pos++], x);
            }
            Assert.assertEquals(10, pos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean check(String pass){
        int c1 = 0, c2 = 0, c3 = 0, c4 = 0;
        for (int i = 0; i < pass.length(); i++) {
            if(pass.charAt(i) >= 'a' && pass.charAt(i) <= 'z'){
                c1++;
            }
            if(pass.charAt(i) >= 'A' && pass.charAt(i) <= 'Z'){
                c2++;
            }
            if(pass.charAt(i) >= '0' && pass.charAt(i) <= '9'){
                c3++;
            }
            if(pass.charAt(i) == '*' || pass.charAt(i) == '!' ||
                    pass.charAt(i) == '%'){
                c4++;
            }
        }
        return pass.length() > 5 && pass.length() < 13 && c1 > 0 && c2 > 0 && c3 > 0 && c4 > 0;
    }

    @Test
    public void testPassGen(){
        File input = new File(pathToResource + "login.txt");
        File output = new File(pathToResource + "pass.txt");
        util.passwordGen(input, output);
        HashSet<String> set = new HashSet<>();
        try {
            Scanner in = new Scanner(output);
            while (in.hasNextLine()){
                String pass = in.nextLine().split(" ")[1];
                Assert.assertTrue(check(pass));
                set.add(pass);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(7, set.size());
    }

    @Test
    public void testAppend(){
        File file = new File(pathToResource + "append.txt");
        ArrayList<String> list = new ArrayList<>();
        for (int i = 4; i < 11; i++) {
            list.add("str" + i);
        }
        util.appender(file, list);
        try {
            Scanner in = new Scanner(file);
            int pos = 1;
            while (in.hasNextLine()){
                String line = in.nextLine();
                Assert.assertEquals(line, "str" + pos);
                pos++;
            }
            Assert.assertEquals(11, pos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNLines(){
        List<String> list =
                util.getNString(pathToResource + "file.txt", 100);
        for (int i = 0; i < 100; i++) {
            Assert.assertEquals("Oleg message " + (i + 900),
                    list.get(i).trim());
        }
    }
}

package classWork.java3_02_2020.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class FieldsTest {



    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class c = String.class;
        String s = "1123";
        /*System.out.println(int.class);
        System.out.println(void.class);*/
        Field [] fields = c.getFields();
        Field [] declaredFields = c.getDeclaredFields();
//        System.out.println("Fields:");
//        List.of(fields).forEach(System.out::println);
//        System.out.println("Declared Fields:");
//        List.of(declaredFields).forEach(System.out::println);
        Field field = c.getDeclaredField("value");
        System.out.println(field);
        System.out.println(field.getName());
        field.setAccessible(true);
        System.out.println(s);
        byte [] value = (byte[]) field.get(s);
        System.out.println(Arrays.toString(value));
        value[1] = '2';
//        ArrayList<Character> list = new ArrayList<>();
//        for (byte v : value) list.add((char)v);
//        list.forEach(System.out::print);
        System.out.println(s);
    }
}

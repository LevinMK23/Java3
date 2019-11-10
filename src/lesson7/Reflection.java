package lesson7;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Reflection {
    public static void main(String[] args) throws NoSuchFieldException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String s = "aaa";
        s.indexOf("asf");
        Class str = s.getClass();
        System.out.println(Arrays.toString(str.getDeclaredFields()));
        System.out.println(str.getDeclaredField("value"));
        Field values = str.getDeclaredField("value");
        System.out.println(values.getName());
        System.out.println(values.getType());
        values.setAccessible(true);
        values.set(s, "aaadfd");
        char [] s1 = (char []) values.get(s);
        s1[2] = 'b';
        for(Method method : str.getMethods()){
            System.out.println(method.getName());
            if(method.getName().equals("indexOf")){
                System.out.println("-----" + method.invoke(s, "aa"));
            }
        }

        System.out.println(s1);
        System.out.println(s);
    }
}

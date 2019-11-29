package classWork.annotation;


import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String s = "abba";
        String s1 = "ccdd";
        Class c = s.getClass();
        System.out.println(c.getName());
        //field
        Field [] fields = c.getFields();
//        for (Field field : c.getDeclaredFields()){
//            System.out.println(field);
//        }
        Field field = c.getDeclaredField("value");
        field.setAccessible(true);
        System.out.println(field.get(s));
//        for (int i = 0; i < ((char [])field.get(s1)).length; i++) {
//            System.out.print(((char [])field.get(s1))[i] + " ");
//        }
        char [] a = (char[]) field.get(s);
        a[1] = 'z';
        System.out.println(s);
    }
}

package classWork.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ExtTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class c = User.class;
        User u = new User("Ivan");
        //Field show get set
//        for (Field field : c.getDeclaredFields()){
//            System.out.println(field);
//        }
//        Field field = c.getDeclaredField("name");
//        field.setAccessible(true);
//        System.out.println(field.get(u).getClass().getName());
//        field.set(u, "Oleg");
//        System.out.println(u);
        //Method show invoke
//        for (Method method : c.getDeclaredMethods()){
//            System.out.println(method);
//        }
        Method method = c.getDeclaredMethod("foo", int.class, float.class, String.class);
        System.out.println(method);
        System.out.println(method.getReturnType());
        method.invoke(u, 15, 1.4f, "String str");
        System.out.println(u);
    }
}

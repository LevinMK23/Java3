package db2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Cat cat = new Cat();
        cat.info();
        Class c = cat.getClass();
//        System.out.println("Fields : ");
//        for (Field field : c.getDeclaredFields()){
//            System.out.print(field.getName() + " : "
//             + field.getType().getName() + " : ");
//            field.setAccessible(true);
//            if(field.getName().equals("id"))
//                field.setInt(cat, 100500);
//            System.out.println(field.get(cat));
//        }
//        Cat ca1 = new Cat();
//        ca1.info();
//        String s = "aaaaa";
//        Class strClass = s.getClass();
//        for(Field field : strClass.getDeclaredFields()){
//            System.out.println(field + " - " + field.getName() + " : " +
//                    field.getType().getName());
//        }
//        Field field = strClass.getDeclaredField("value");
//        System.out.println(field);
//        field.setAccessible(true);
//        char [] chars = (char [])field.get(s);
//        System.out.println(chars);
//        chars[2] = 'b';
//        System.out.println(s);
//        System.out.println(void.class);
//        System.out.println(int[].class);
//        System.out.println(double[].class);
//        System.out.println(byte[].class);
//        System.out.println(boolean[][][].class);

        System.out.println("Methods : ");
        for(Method method : c.getDeclaredMethods()){
            //System.out.println(method.getName());
            if(method.isAnnotationPresent(MyAnnotation.class)){
                System.out.println(method.invoke(cat));
            }
        }
//        Method m1 = c.getDeclaredMethod("difficultMethod",
//                new Class[]{String.class, int.class});
//        m1.invoke(cat, new Object[]{"asd", 12});
//        System.out.println("Constructors : ");
//        for(Constructor constructor : c.getDeclaredConstructors()){
//            System.out.println(constructor);
//            //Cat c1 = (Cat) constructor.newInstance();
//        }
//        Cat c1 = (Cat)c
//                .getConstructor(new Class[]{String.class, int.class, int.class})
//                .newInstance(new Object[]{"Vaska", 1, 2});
//        c1.info();
//        Cat c2 = (Cat)c
//                .getConstructor(new Class[]{String.class})
//                .newInstance(new Object[]{"Vaska"});
//        c2.info();
//        Cat c3 = (Cat)c
//                .getConstructor()
//                .newInstance();
//        c3.info();
    }
}

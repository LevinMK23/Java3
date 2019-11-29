package classWork.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class ExTest2 {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        User u = new User("Ivan");
        Class us = u.getClass();

//        for(Constructor constructor : us.getDeclaredConstructors()){
//            System.out.println(constructor);
//        }
//        Constructor constructor = us.getConstructor(String.class);
//        Object u2 = constructor.newInstance("Oleg");
//        System.out.println(u2);
        ArrayList<Method> list = new ArrayList<>();
        for (Method method : us.getDeclaredMethods()){
            if (method.isAnnotationPresent(MyAnnotation.class)){
                list.add(method);
                System.out.println(method.getAnnotation(MyAnnotation.class).value());
                Class [] classes = method.getParameterTypes();
                //method.invoke(u, 12, 2f, "Str");
            }
        }
        list.sort(Comparator.comparing(o->o.getAnnotation(MyAnnotation.class).value()));
        for (Method m : list){
            m.invoke(u);
        }
    }
}

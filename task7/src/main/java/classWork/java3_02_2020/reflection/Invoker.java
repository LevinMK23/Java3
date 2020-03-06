package classWork.java3_02_2020.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Invoker {

    public static Object invokeByNameAndParam(Object object, String methodName, Class<?>[] types,
                                              Object[] params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> c = object.getClass();
        System.out.println(c);
        Method method = c.getDeclaredMethod(methodName, types);
        return method.invoke(object, params);
    }

    int foo(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //"1234".indexOf("2") -> 1
        //int indexOf(String str)
        System.out.println(invokeByNameAndParam(new Invoker(),
                "foo", new Class[]{int.class, int.class},
                new Object[]{4, 5}));
    }
}

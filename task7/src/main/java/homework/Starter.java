package homework;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class Starter {
    static void test(Test test) {
        Class<?> cl = test.getClass();
        Method first = null, last = null;
        ArrayList<Method> tests = new ArrayList<>();
        Method [] methods = cl.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) {
                first = method;
            }
            if (method.isAnnotationPresent(After.class)) {
                last = method;
            }
            if (method.isAnnotationPresent(TestCase.class)) {
                tests.add(method);
            }
        }
        tests.sort(Comparator.comparingInt(method -> -method.getAnnotation(TestCase.class
        ).priority()));
        try {
            first.invoke(test);
            int cnt = 1;
            for (Method method : tests) {
                System.out.println("Test " + cnt + " started");
                method.invoke(test);
                System.out.println("Test " + cnt + " finished");
                cnt++;
            }
            last.invoke(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

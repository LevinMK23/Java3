package classWork.java3_02_2020.annotation;

import java.lang.reflect.Method;

public class AnnotateInfo {
    public static void main(String[] args) {
        Class c = A.class;
        for (Method m : c.getDeclaredMethods()) {
            System.out.println(m.getAnnotation(Black.class));
        }
    }
}

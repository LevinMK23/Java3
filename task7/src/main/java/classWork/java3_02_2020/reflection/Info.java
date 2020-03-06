package classWork.java3_02_2020.reflection;

import java.lang.reflect.Field;

public class Info {
    public static void showInfo(Object o) throws IllegalAccessException {
        Class<?> c = o.getClass();
        Field [] fields = c.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field + " " + field.get(o));
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        //showInfo(new User());
    }
}

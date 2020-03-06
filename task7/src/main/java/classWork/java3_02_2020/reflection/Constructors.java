package classWork.java3_02_2020.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Constructors {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> c = User.class;
        Constructor con = c.getConstructor(String.class);
        User user = (User) con.newInstance("Ivan");
        System.out.println(user.getName());
    }
}

package classwork;

import java.util.Comparator;

public class LambdaClass {

    @FunctionalInterface
    interface FuncInterface {
        void foo(int param);
    }

    public static void main(String[] args) {
        FuncInterface foo = System.out::println;
        FuncInterface foo1 = new FuncInterface() {
            @Override
            public void foo(int param) {

            }
        };

        //apply
        foo.foo(15);
        Comparator<User> comparator = Comparator.comparing(User::getName);
        System.out.println(foo.getClass().getName());
        System.out.println(foo1.getClass().getName());
    }
}

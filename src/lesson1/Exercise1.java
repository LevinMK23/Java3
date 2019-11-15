package lesson1;

import java.util.Arrays;
import java.util.Scanner;

public class Exercise1 {

    //without gen
    //alt + insert win
    //cmd + N mac OS
    Object object;

    public Exercise1(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public static void main(String[] args) {
        Exercise1 ex1 = new Exercise1(1L);
        Exercise1 ex2 = new Exercise1(2.5);
        Exercise1 ex3 = new Exercise1(2.5f);
        Exercise1 ex4 = new Exercise1("string");
        Exercise1 ex5 = new Exercise1(new Scanner(System.in));
        ex5.setObject(15);
        System.out.println(ex1.getObject().getClass().getName());
        System.out.println(ex2.getObject().getClass().getName());
        System.out.println(ex3.getObject().getClass().getName());
        System.out.println(ex4.getObject().getClass().getName());
        System.out.println(ex5.getObject().getClass().getName());
        long val = ((Long)(ex1.getObject()));
        if(ex4.getObject() instanceof Integer){
            int val1 = (int) ex4.getObject();
            System.out.println(val1);
        }
        if(ex4.getObject() instanceof String){
            String str = ex4.getObject() + " concat";
            System.out.println(str);
        }




    }
}

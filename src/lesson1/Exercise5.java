package lesson1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class Exercise5{

    //compare
    static Glass<Water> waterGlass;
    static Glass<Acid> acidGlass;

    public Exercise5() {
        waterGlass = new Glass<>(new Water(1, 1.7f));
        acidGlass = new Glass<>(new Acid(1, 1.3f));
    }

    public int compare(Glass<? extends Liquid> a, Glass<? extends Liquid> b){
        return a.liquid.compareTo(b.liquid);
    }

    public static void main(String[] args) {
        System.out.println(new Exercise5().compare(waterGlass, acidGlass));
    }
}

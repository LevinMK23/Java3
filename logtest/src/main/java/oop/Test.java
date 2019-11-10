package oop;

public class Test {
    public static void main(String[] args) {
//        Triangle t1 = new Triangle(3,4,5);
//        t1.printInfo();
//        Triangle t2 = new Triangle(3,4,12);
//        t2.printInfo();
        Cat cat = new Cat("Vaska");
        Cat cat2 = new Cat();
        Cat cat3 = new Cat("Vaska", 5);
        cat.info();
        cat2.info();
        cat3.info();
        cat.say(2f, 4);
        cat.say(2, 1f);
    }
}

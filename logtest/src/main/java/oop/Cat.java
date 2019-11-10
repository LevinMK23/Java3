package oop;

public class Cat {

    private String name; //свойства кота
    private int age;     //свойства кота

    //package private
    public Cat(){
        name = "noname";
        age = 100500;
    }

    public Cat(String n){
        name = n;
        age = 0;
    }

    public Cat(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void live(int time){
        age += time;
    }

    public void say(){  //метод кота (классовая функция)
        System.out.println("MEOW");
    }

    public void say(String message){
        System.out.println(message);
    }

    public void say(int value){
        for (int i = 0; i < value; i++) {
            System.out.println("MEOW");
        }
    }

    public void say(int left, float right){
        System.out.println("if");
    }

    public void say(float left, int right){
        System.out.println("fi");
    }

    public void say(int left, int right){
        for (int i = left; i <= right; i++) {
            System.out.println(i);
        }
    }

    public void info(){
        System.out.println(name + " " + age);
    }
}

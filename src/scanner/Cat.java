package scanner;

public class Cat extends Pet{

    public Cat() {
        //System.out.println(this + " created!");
    }

    public Cat(String name) {
        this.name = name;
        System.out.println(this.name + " created!");
    }

    public Cat(int age, String name){
        this.age = age;
        this.name = name;
        System.out.println(this.name + " created! with age " + this.age);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(this.name + " deleted!");
    }

    @Override
    public String toString() {
        return String.format("Cat{%d, %s}", age, name);
    }

    @Override
    public void say() {
        System.out.println("MEOW!");
    }
}

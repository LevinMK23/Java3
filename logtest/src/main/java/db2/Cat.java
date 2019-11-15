package db2;

@BaseTable(name = "cats")
public class Cat {
    //Reflection
    @BaseItem
    public static int id = 0;
    @BaseItem
    public String name, msg;
    @BaseItem
    public int hp;
    @BaseItem
    public int age;

    public Cat() {
        id++;
        msg = "MEOW";
    }

    public Cat(String name) {
        id++;
        this.name = name;
        msg = "MEOW";
    }

    public Cat(String name, int hp, int age) {
        this.name = name;
        this.hp = hp;
        this.age = age;
        id++;
        msg = "MEOW";
    }

    private void say(){
        System.out.println(msg);
    }

    public void time(){
        age++;
    }

    @MyAnnotation(mark = "AA", rang = 4)
    public int getAge(){
        return age;
    }

    public void difficultMethod(String str, int val){
        System.out.println(str + " : " + val);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                ", hp=" + hp +
                ", age=" + age +
                '}';
    }

    public void info(){
        System.out.println(this);
    }

}

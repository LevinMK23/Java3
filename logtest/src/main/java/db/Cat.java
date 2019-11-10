package db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@XTable(title = "cats")
public class Cat {

    @XField
    public int age;

    @XField
    public String name;

    @XField
    public int hp;

    @XField
    public int id;

    public Cat() {
    }

    public Cat(int age, String name, int hp, int id) {
        this.age = age;
        this.name = name;
        this.hp = hp;
        this.id = id;
    }
}

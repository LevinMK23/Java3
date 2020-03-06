package classWork.java3_02_2020.hibernate;

public class User {

    @HiberField(type = "TEXT")
    String name;

    @HiberField(type = "INTEGER")
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

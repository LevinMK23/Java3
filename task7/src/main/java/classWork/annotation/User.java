package classWork.annotation;

public class User {

    private int id;
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }


    public void foo(int a, float b, String s){
        System.out.println(a + b + " " + s);
    }

    @MyAnnotation(value = 12)
    public void foo2(){
        System.out.println(2);
    }

    @MyAnnotation(value = 15)
    public void foo1(){
        System.out.println(1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

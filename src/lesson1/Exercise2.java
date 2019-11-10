package lesson1;

public class Exercise2<Type> {

    //generic
    //simple gen

    Type object;

    public Exercise2(Type object) {
        this.object = object;
    }

    public Type getObject() {
        return object;
    }

    public void setObject(Type object) {
        this.object = object;
    }

    public static void main(String[] args) {
        Exercise2<Integer> ex1 = new Exercise2<>(55);
        System.out.println(ex1.getObject());
        Exercise2<String> ex2 = new Exercise2<>("str");
        ex1.setObject(12);
        System.out.println(ex1.getObject().getClass().getName());
    }
}

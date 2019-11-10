package oop;

public class Triangle {

    private double a, b, c, cosA, cosB, cosC;
    private boolean error;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        error = false;
        if(a + b < c || a + c < b || c + b < a){
            error = true;
            System.out.println("Construction error!");
        }
    }

    public void setSide(String label, double value){
        if(label.equals("a")){

        }
        if(label.equals("b")){
            c = value;
        }
        if(label.equals("c")){

        }
    }

    public void printInfo(){
        if(error){
            System.out.println("Triangle not exists!");
        }
        else {
            System.out.println(a + " " + b + " " + c);
        }
    }
}

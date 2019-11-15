package lesson1;

public class Liquid {

    float volume;
    float m, p;

    public Liquid(float volume, float m) {
        this.volume = volume;
        this.m = m;
    }

    public float getP(){
        return m / volume;
    }

    public int compareTo(Liquid otherLiquid){
        return Float.compare(getP(), otherLiquid.getP());
    }
}

package lesson1;

public class Glass<V extends Liquid> {

    V liquid;

    public Glass(V liquid) {
        this.liquid = liquid;
    }

    public V getLiquid() {
        return liquid;
    }

    public void setLiquid(V liquid) {
        this.liquid = liquid;
    }
}

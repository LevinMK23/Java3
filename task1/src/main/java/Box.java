import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {

    private List<T> fuits = new ArrayList<>();

    public float getWeight(){
        float weight = 0.0f;
        for (int i = 0; i < fuits.size(); i++) {
            weight += fuits.get(i).getWeight();
        }
        return weight;
    }

    public boolean compareTo(Box<? extends Fruit> other){
        if (this.getWeight() == other.getWeight()) return true;
        return false;
    }

    public void addFruit(T fruit){
        fuits.add(fruit);
    }

    public void dropFruits(Box<T> otherBox){
        while (!this.fuits.isEmpty()){
            otherBox.addFruit(this.fuits.remove(0));
        }
    }
}

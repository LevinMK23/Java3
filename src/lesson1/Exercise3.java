package lesson1;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class Exercise3<K, V> {
    //two gen
    K key;
    V value;

    public Exercise3(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public static void main(String[] args) {
        Exercise3<Integer, String> ex1 = new
                Exercise3<>(2, "lol");
        System.out.println(ex1.getKey());
        System.out.println(ex1.getValue());
//        for (int i = 1000000; i < 1100000; i++) {
//            ex1.setKey(i);
//            System.out.println(ex1.getKey().hashCode());
//        }
    }
}

package lesson1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Exercise4<T extends Number & Comparable<T>> {

    T obj;
    ArrayList<T> list;

    public Exercise4(T obj) {
        this.obj = obj;
        list = new ArrayList<>();
    }

    int lengthDif(List<?> otherList){
        return list.size() - otherList.size();
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public boolean equals(T other){
        return obj.compareTo(other) == 0;
    }

    public static void main(String[] args) {
        Exercise4<Float> ex = new Exercise4<>(30f);
        Exercise4<Long> ex1 = new Exercise4<>(30L);
        System.out.println(ex.getObj()
                .compareTo(ex1.getObj().floatValue()));
        System.out.println(ex.equals(ex1.getObj().floatValue()));
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add("(long)" + i);
        }
        int dif = ex.lengthDif(list);
        System.out.println(dif);
    }
    //extends, ? &

}

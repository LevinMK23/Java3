package scanner;

import java.io.IOException;
import java.util.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        HashMap<String, IntList> map = new HashMap<>();
        String [] words = new Scanner(System.in)
                .nextLine().toLowerCase().split("\\W");
        int cnt = 1;
        for (String word : words){
            if(!word.isEmpty()){
                if(map.containsKey(word)){
                    map.get(word).add(cnt);
                }
                else{
                    IntList list = new IntList();
                    list.add(cnt);
                    map.put(word, list);
                }
                cnt++;
            }
        }
//        for(Map.Entry<String, LinkedList<Integer>> entry : map.entrySet()){
//            System.out.print(entry.getKey() + " " + entry.getValue().size() + " ");
//            for(Integer pos : entry.getValue()){
//                System.out.print(pos + " ");
//            }
//            System.out.println();
//        }
        for (String word : words){
            if(!word.isEmpty()){
                if(map.containsKey(word)) {
                    System.out.print(word + " " + map.get(word).size() + " ");
                    for (Integer pos : map.get(word)) System.out.print(pos + " ");
                    System.out.println();
                    map.remove(word);
                }
            }
        }
    }
}

package test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class WordStatLineIndex {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        TreeMap<String, LinkedList<String>> map = new TreeMap<>();
        int lineCounter = 0;
        while (in.hasNextChar()){
            String [] line = in.nextLine().split("( +)");
            lineCounter++;
            for (int i = 0; i < line.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < line[i].length(); j++) {
                    line[i] = line[i].toLowerCase();
                    if(Character.isAlphabetic(line[i].charAt(j)) ||
                            line[i].charAt(j) == '\''){
                        sb.append(line[i].charAt(j));
                    }
                }
                line[i] = sb.toString();
                if(!line[i].isEmpty()) {
                    String value = String.format("%s:%s", lineCounter, i + 1);
                    map.put(line[i], map.getOrDefault(line[i], new LinkedList<>()));
                    map.get(line[i]).add(value);
                }
            }
        }
        for(Map.Entry<String, LinkedList<String>> entry : map.entrySet()){
            for(String str : entry.getValue()){
                System.out.println(entry.getKey() + " " + str);
            }
        }
//        map.forEach((key, value) -> {
//            //System.out.print(key + " ");
//            value.forEach((val) -> System.out.println(key + " " + val));
//            //System.out.println();
//        });
    }
}

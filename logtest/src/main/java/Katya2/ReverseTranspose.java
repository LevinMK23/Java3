package Katya2;

import test.FastScanner;

import java.util.*;

public class ReverseTranspose {

    static LinkedList<String> split(String s){
        StringBuilder sb = new StringBuilder();
        LinkedList<String> list = new LinkedList<>();
        int pos = 0;
        while (pos < s.length()){
            if(s.charAt(pos) != ' '){
                sb.append(s.charAt(pos));
                if (pos == s.length()-1){
                    list.add(sb.toString());
                    return list;
                }
            }
            else {
                if(sb.length() > 0) list.add(sb.toString());
                sb = null;
                sb = new StringBuilder();
                while (pos < s.length() && s.charAt(pos) == ' ') pos++;
                pos--;
            }
            pos++;
        }
        return list;
    }

    public static void main(String[] args) {
        FastScanner in;
        try {
            in = new FastScanner(System.in);
            LinkedList<LinkedList<String>> list = new LinkedList<>();
            LinkedList<String> data = new LinkedList<>();
            while (in.hasNextChar()) {
                data.add(in.nextLine());
            }
            int max = 0;
            int cnt = 0;
            for (String s : data) {
                cnt = 0;
                LinkedList<String> tmp = new LinkedList<>(split(s));
                for (String ss : tmp) {
                    if (list.size() < tmp.size()) list.add(new LinkedList<>());
                    list.get(cnt).add(ss);
                    cnt++;
                }
            }
            for (LinkedList<String> s : list) {
                for (String ss : s) {
                    System.out.print(ss + " ");
                }
                System.out.println();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
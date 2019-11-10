package test;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

//import java.util.Scanner;

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

    public static int[] stringToIntArray(String string) {

        if (string.isEmpty()) {
            return new int[0];
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(-1);
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ' ') {
                queue.add(i);
            }
        }
        int[] array = new int[queue.size()];
        queue.add(string.length());
        int i = 0;
        while (queue.size() > 1) {
            array[i++] = Integer.parseInt(string.substring(queue.remove() + 1, queue.peek()));
        }
        return array;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<List<Integer>> matrix = new ArrayList<>();
        int [] nums;
        FastScanner scanner = new FastScanner(System.in);
        while (scanner.hasNextChar()) {
            nums = stringToIntArray(scanner.nextLine());
            for (int i = 0; i < nums.length; i++) {
                if (matrix.size() <= i) {
                    matrix.add(new ArrayList<>());
                }
                matrix.get(i).add(nums[i]);
            }
        }

        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out), 4096)) {
            for (List<Integer> list : matrix) {
                for (Integer i : list) {
                    out.write(i + " ");
                }
                out.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
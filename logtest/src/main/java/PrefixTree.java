import java.util.Arrays;
import java.util.Random;

public class PrefixTree {

    private int size, j, memo;

    private class Node{
        Node [] data;
        int value;

        public Node() {
            data = new Node[26];
            memo += 8 * 26 + 4;
            value = 0;
        }
    }

    public int getMemo() {
        return memo;
    }

    private Node root;

    public PrefixTree() {
        root = new Node();
        size = 0;
        j = 0;
    }

    private void put(Node node, String word, int i){
        if(i == word.length()) {
            size++;
            return;
        }
        if(node == null){
            node = new Node();
        }
        if (node.data[word.charAt(i) - 'a'] == null){
            node.data[word.charAt(i) - 'a'] = new Node();
            if(i == word.length()-1){
                node.data[word.charAt(i) - 'a'].value++;
            }else put(node.data[word.charAt(i) - 'a'], word, i + 1);
        }
        else{
            if(i == word.length()-1){
                node.data[word.charAt(i) - 'a'].value++;
            }else put(node.data[word.charAt(i) - 'a'], word, i + 1);
        }
    }

    public void put(String word){
        put(root, word, 0);
    }

    private boolean remove(Node node, String word, int len){
        if(len < word.length()-1) {
            return remove(node.data[word.charAt(len) - 'a'], word, len + 1);
        }
        if(len == word.length()-1) {
            if(node.data[word.charAt(len) - 'a'].value > 0){
                node.data[word.charAt(len) - 'a'].value--;
                return true;
            }
            else return false;
        }
        else return false;
    }

    public boolean remove(String word){
        return  remove(root, word, 0);
    }

    private boolean contains(Node node, String word, int i){
        if(i == word.length()) return true;
        if(node.data[word.charAt(i)-'a'] == null) return false;
        else{
            return contains(node.data[word.charAt(i)-'a'], word, i+1);
        }
    }

    private void show(Node node, String prefix, int cnt){
        for (int i = 0; i < 26; i++) {
            if(node.data[i] != null){
                if(node.data[i].value > 0) System.out.println(prefix + (char)('a' + i) + " : " + node.data[i].value);
                show(node.data[i], prefix + (char)('a' + i), cnt + 1);
            }
        }
    }

    public int countWordsWithPrefix(String prefix){
        countWordsWithPrefix(root, prefix, 0);
        int res = acc;
        acc = 0;
        return res;
    }

    private static int acc = 0;

    private void countWordsWithPrefix(Node node, String prefix, int i) {
        if(!contains(prefix)) return;
        while (i < prefix.length()){
            acc += node.data[prefix.charAt(i)-'a'].value;
            node = node.data[prefix.charAt(i)-'a'];
            i++;
        }
        for (int x = 0; x < 26; x++) {
            if(node.data[x] != null){
                acc += node.data[x].value;
                countWordsWithPrefix(node.data[x], prefix, i);
            }
        }
    }

    public void show(){
        show(root, "", 0);
    }

    public boolean contains(String word){
        return contains(root, word, 0);
    }

    public static void main(String[] args) {
        PrefixTree tree = new PrefixTree();
        tree.put("aaa");
        tree.put("aab");
        tree.put("aac");
        tree.put("aaa");
        tree.put("bcx");
        tree.put("bxc");
        tree.put("xbc");
        tree.show();
        System.out.println(tree.contains("abaz"));
        System.out.println(tree.contains("abb"));
        System.out.println(tree.contains("abc"));
        System.out.println(tree.countWordsWithPrefix("aaa"));
        System.out.println("Used memory : " + tree.getMemo() + "b");
    }
}

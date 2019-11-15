package scanner;

public class TestList {
    public static void main(String[] args) {
        IntList list = new IntList();
        for (int i = 0; i < 15; i++) {
            list.add(i);
        }
        for (Integer i : list) {
            System.out.print(i + " "); // O(n)
        }
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " "); // O(n ^ 2)
        }
    }
}

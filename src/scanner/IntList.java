package scanner;

import java.util.Iterator;

public class IntList implements Iterable<Integer>{

    private Node head, tail, tmpNode;
    private int size;
    private Iterator<Integer> iterator;

    public Iterator<Integer> iterator() {
        tmpNode = head;
        iterator = new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return hasNext1();
            }

            @Override
            public Integer next() {
                return next1();
            }
        };
        return iterator;
    }

    private boolean hasNext1() {
        return tmpNode != null;
    }


    private Integer next1() {
        Integer value = tmpNode.value;
        tmpNode = tmpNode.next;
        return value;
    }


    private class Node{

        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public IntList() {
        head = tail = null;
        size = 0;
    }

    public void add(int element){
        Node tmp = new Node(element);
        if(size == 0){
            head = tail = tmp;
            size++;
        }
        else{
            tail.next = tmp;
            tail = tmp;
            size++;
        }
    }

    public int size(){
        return size;
    }

    private int get(Node node, int position){
        int start = 0;
        while (start != position){
            node = node.next;
            start++;
        }
        return node.value;
    }

    public int get(int position){
        return get(head, position);
    }

    private void show(Node node){
        if(node != null){
            System.out.print(node.value + " ");
            show(node.next);
        }
    }

    public void show(){
        show(head);
    }
}

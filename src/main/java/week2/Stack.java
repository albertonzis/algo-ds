package week2;

public class Stack<T> {

    private Node first = null;

    private class Node {
        T item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(T item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public T pop() {
        if (isEmpty()) throw new RuntimeException("Trying to pop an empty stack");
        T item = first.item;
        first = first.next;
        return item;
    }


    public static void main(String[] args){
        Stack<Integer> stackInt = new Stack<>();
        stackInt.push(1);
        stackInt.push(2);
        stackInt.push(3);
        stackInt.push(4);
        while (!stackInt.isEmpty()) {
            System.out.println(stackInt.pop());
        }

        Stack<String> stackString = new Stack<>();
        stackString.push("a");
        stackString.push("b");
        stackString.push("c");
        stackString.push("d");
        while (!stackString.isEmpty()) {
            System.out.println(stackString.pop());
        }
    }

}

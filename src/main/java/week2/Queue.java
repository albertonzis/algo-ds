package week2;

public class Queue<T> {

    private Node first = null;
    private Node last = null;

    private class Node {
        T item;
        Node next;
    }

    public Queue() {

    }

    public void enqueue(T item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    public T dequeue() {
        T item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public static void main(String[] args) {
        Queue<String> qS = new Queue<>();
        qS.enqueue("a");
        qS.enqueue("b");
        qS.enqueue("c");
        while(!qS.isEmpty()) System.out.println(qS.dequeue());

        Queue<Integer> qI = new Queue<>();
        qI.enqueue(10);
        qI.enqueue(20);
        qI.enqueue(30);
        while(!qI.isEmpty()) System.out.println(qI.dequeue());
    }

}

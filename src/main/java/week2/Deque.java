package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;

    private int size;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    // construct an empty deque
    public Deque() {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null && last == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        validate(item);
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (last == null) last = first;
        else oldFirst.previous = first;
        ++size;
    }

    // add the item to the back
    public void addLast(Item item) {
        validate(item);
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        if (first == null) first = last;
        else oldLast.next = last;
        ++size;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (first != null) first.previous = null;
        else last = null;
        --size;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        if (last != null) last.next = null;
        else first = null;
        --size;
        return item;
    }


    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }


    private class DequeIterator implements Iterator<Item> {

        Node currFirst = first;

        @Override
        public boolean hasNext() {
            return currFirst != null;
        }

        @Override
        public Item next() {
            if (currFirst == null) throw new NoSuchElementException();
            Item item = currFirst.item;
            currFirst = currFirst.next;
            return item;
        }
    }

    private void validate(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deck = new Deque<>();

        for (int i = 0; i < 50; i++) {
            if (StdRandom.bernoulli()) deck.addFirst(i);
            else deck.addLast(i);
        }


        Iterator<Integer> it = deck.iterator();
        while (it.hasNext()) {
            System.out.printf("%s ",it.next());
        }

        System.out.print("\n");

        for (Integer i : deck) {
            System.out.printf("%s ", i);
        }

        System.out.printf("\nSize: %s\n", deck.size());

        while(!deck.isEmpty()) {
            if (StdRandom.bernoulli()) System.out.printf("RF: %s\n", deck.removeFirst());
            else System.out.printf("RL: %s\n", deck.removeLast());
        }

    }
}
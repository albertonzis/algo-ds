package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (items.length == size()) {
            resize(items.length * 2);
        }
        items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int randomInt;
        do {
            randomInt = StdRandom.uniformInt(0, items.length);
        } while (items[randomInt] == null);
        Item item = items[randomInt];
        items[randomInt] = null;
        --size;
        if (size == items.length/4) resize(items.length / 2);
        return item;
    }


    // return a random item (but do not remove it)
    public Item sample() {
        int randomInt;
        do {
            randomInt = StdRandom.uniformInt(0, items.length);
        } while (items[randomInt] == null);
        return items[randomInt];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        int counter = 0;
        int[] indexes = new int[size];

        @Override
        public boolean hasNext() {
            return counter < size;
        }

        @Override
        public Item next() {
            int randomInt;
            do {
                randomInt = StdRandom.uniformInt(0, size);
            } while (items[randomInt] == null || indexes[randomInt] == 1);
            indexes[randomInt] = 1;
            counter++;
            return items[randomInt];
        }
    }

    private void resize(int capacity) {
        Item[] newItems = (Item[]) new Object[capacity];
        int j = 0;
        for (Item item : items) {
            if (item != null) newItems[j++] = item;
        }
        items = newItems;
    }
        // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rqi = new RandomizedQueue<>();

        for(int i = 0; i < 100; i++) {
            rqi.enqueue(i);
        }

        Iterator<Integer> it = rqi.iterator();
        int i = 0;
        while (it.hasNext()){
            System.out.printf("Next1-%s: %s\n", i++, it.next());
        }

        it = rqi.iterator();
        while (it.hasNext()){
            System.out.printf("Next2-%s: %s\n", i++, it.next());
        }

        System.out.printf("Size: %s\n",rqi.size());
        System.out.printf("Sample: %s\n", rqi.sample());
        System.out.printf("Sample: %s\n", rqi.sample());
        System.out.printf("Sample: %s\n", rqi.sample());
        System.out.printf("Size: %s\n",rqi.size());

        while (!rqi.isEmpty()) {
            System.out.printf("Dequeue: %s\n", rqi.dequeue());
        }

        System.out.printf("Size: %s\n",rqi.size());



    }

}
package week2;

import edu.princeton.cs.algs4.StdRandom;

public class QueueWithTwoStacks<Item> {

    private Stack<Item> enqueue;
    private Stack<Item> dequeue;

    public QueueWithTwoStacks() {
        enqueue = new Stack<>();
        dequeue = new Stack<>();
    }

    public void enqueue(Item item) {
        enqueue.push(item);
    }

    public Item dequeue() {
        if (dequeue.isEmpty()) {
            while (!enqueue.isEmpty()) {
                dequeue.push(enqueue.pop());
            }
        }
        return dequeue.pop();
    }

    public boolean isEmpty() {
        return enqueue.isEmpty() && dequeue.isEmpty();
    }

    public static void main(String[] args) {
        QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<>();
        for (int i = 1; i <= 15; i++) {
            queue.enqueue(i);
            System.out.printf("[E]: %s\n", i);
            if (StdRandom.bernoulli(0.65)) System.out.printf("[D]: %s\n", queue.dequeue());
        }
        while (!queue.isEmpty()) {
            System.out.printf("[D*]: %s\n", queue.dequeue());
        }
    }
}

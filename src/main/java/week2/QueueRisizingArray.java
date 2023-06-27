package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Objects;

public class QueueRisizingArray<T> {

    private T[] q;
    private Integer head;
    private Integer tail;

    public QueueRisizingArray() {
        q = (T[]) new Object[1];
        head = 0;
        tail = 0;
    }

    public void enqueue(T item) {
        if (q[q.length-1] != null) {
            resize(q.length * 2);
            tail = tail - head;
            head = 0;
        }
        tail %= q.length;
        q[tail] = item;
        ++tail;
    }

    public T dequeue() {
        head %= q.length;
        T item = q[head];
        q[head] = null;
        ++head;
        if (head == q.length) head = 0;
        else if (tail-head+1 <= q.length/4) {
            resize(q.length/2 );
            tail = tail - head;
            head = 0;
        }
        return item;
    }

    public boolean isEmpty() {
        return q[head] == null;
    }

    private void resize(int capacity) {
        T[] copy = (T[]) new Object[capacity];
        int j = 0;
        for (int i = 0; i < q.length; i++) {
            if (q[i] != null) copy[j++] = q[i];
        }
        q = copy;
    }


    private void printArray(String type) {
        System.out.printf("Array after %s: ", type);
        for(T print: q){
            System.out.printf("%s ", print);
        }
        System.out.printf("\n");
    }

    public static void main(String[] args) {
        QueueRisizingArray<Integer> qra = new QueueRisizingArray<>();
        for (int i = 1; i <= 15; i++ ) {
            qra.enqueue(i);
            System.out.printf("[E]nqueueing: %s\n", i);
            if (StdRandom.bernoulli(0.55)) System.out.printf("[D]equeueing: %s\n", qra.dequeue());
        }

        while(!qra.isEmpty()) {
            System.out.printf("[D]equeueing rest: %s\n", qra.dequeue());
        }

    }


}

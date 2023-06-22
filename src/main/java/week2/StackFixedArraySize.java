package week2;

public class StackFixedArraySize<T> {

    private T[] s;
    private int N = 0;

    public StackFixedArraySize(int size) {
        s = (T[]) new Object[size];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(T item) {
        s[N++] = item;
    }

    public T pop() {
        if (isEmpty()) throw new RuntimeException("Trying to pop an empty stack");
        return s[--N];
    }

    public static void main(String[] args) {
        StackFixedArraySize<Integer> si = new StackFixedArraySize<>(4);
        si.push(1);
        si.push(2);
        si.push(3);
        si.push(4);
        si.push(5);
        while (!si.isEmpty()) {
            System.out.println(si.pop());
        }
    }
}

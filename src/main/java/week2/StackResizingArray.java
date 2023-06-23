package week2;

public class StackResizingArray<Item> {

    private Item[] s;
    private int N = 0;

    public StackResizingArray() {
        s = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    }

    public Item pop() {
        if (isEmpty()) throw new RuntimeException("Trying to pop an empty stack");
        Item item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length/4) resize(s.length/2);
        return item;
    }

    public int size() {
        return s.length;
    }

    private void resize(int length) {
        Item[] copy = (Item[]) new Object[length];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    public static void main(String[] args) {
        StackResizingArray<Integer> si = new StackResizingArray<>();
        for(int i = 1; i <= 10; i++){
            si.push(i);
            System.out.printf("Pushing: %s\nArray size: %s\n\n", i, si.size());
        }
        while (!si.isEmpty()) {
            System.out.printf("Poping : %s\nArray size: %s\n\n", si.pop(), si.size());
        }
    }
}

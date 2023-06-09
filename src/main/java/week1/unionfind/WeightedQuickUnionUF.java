package week1.unionfind;

public class WeightedQuickUnionUF implements UnionFind {

    private int[] id;
    private int[] sz;
    private int[] gt;

    public WeightedQuickUnionUF(int n) {
        id = new int[n];
        sz = new int[n];
        gt = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        int element = i;
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }

        if (gt[i] < element)
            gt[i] = element;

        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
    
    public int find(int i){
        return gt[root(i)];
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

}

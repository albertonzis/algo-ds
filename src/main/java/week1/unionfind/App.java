package week1.unionfind;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class App {
    
    static int n = 100000;

    public static void main(String[] args) {
        dynamicConnectivity(new QuickFindUF(n));
        dynamicConnectivity(new QuickUnionUF(n));
        dynamicConnectivity(new QuickUnionWeightedUF(n));
    }

    private static void dynamicConnectivity(UnionFind dc){
        Instant start = Instant.now();
        for( int i = 0; i < n; i++) {
            int r1 = new Random().nextInt(n);
            int r2 = new Random().nextInt(n);
            dc.union(r1, r2);
        }
        System.out.printf("%s union time: %s %n", dc.getName(), Duration.between(start, Instant.now()).toMillis());
        start = Instant.now();
        for( int i = 0; i < n; i++) {
            int r1 = new Random().nextInt(n);
            int r2 = new Random().nextInt(n);
            dc.connected(r1, r2);
        }
        System.out.printf("%s connected time: %s %n", dc.getName(), Duration.between(start, Instant.now()).toMillis());
    }
}

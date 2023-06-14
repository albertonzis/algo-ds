package week1.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private Percolation p;
    private int size;
    private double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (trials <= 0) throw new IllegalArgumentException("Trials should ne greater than 0");
        this.size = n * n;
        this.results = new double[trials];
        for (int i = 0; i < trials; i++) {
            p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniformInt(1, n+1);
                int col = StdRandom.uniformInt(1, n+1);
                p.open(row, col);

            }
            this.results[i] = (double) p.numberOfOpenSites() / size;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(results.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(results.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats p = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.printf("mean                    = %s%n", p.mean());
        System.out.printf("stddev                  = %s%n", p.stddev());
        System.out.printf("95p confidence interval = [%s, %s]%n", p.confidenceLo(), p.confidenceHi());
    }

}

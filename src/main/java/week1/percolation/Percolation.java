package week1.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int openSites = 0;
    private int gridSize;
    private WeightedQuickUnionUF wufGrid;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Grid size should bigger than 0");
        gridSize = n;
        grid = new boolean[n+1][n+1];
        wufGrid = new WeightedQuickUnionUF(n*n+2);
//        id = new boolean[][(n*n)+2];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row);
        validate(col);

        if (isOpen(row, col)) return;
        grid[row][col] = true;
        openSites++;
        int index = getIndex(row, col);

        if (col != gridSize && isOpen(row, col+1))
            wufGrid.union(index, getIndex(row, col+1));

        if (col != 1 && isOpen(row, col-1))
            wufGrid.union(index, getIndex(row, col-1));

        if (row != 1 && isOpen(row-1, col))
            wufGrid.union(index, getIndex(row-1, col));

        if (row != gridSize && isOpen(row+1, col))
            wufGrid.union(index, getIndex(row+1, col));

        if (row == 1) wufGrid.union(index, 0);

        if (row == gridSize) wufGrid.union(index, gridSize * gridSize + 1);

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row);
        validate(col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row);
        validate(col);
        return wufGrid.find(getIndex(row, col)) == wufGrid.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wufGrid.find(0) == wufGrid.find(gridSize * gridSize + 1);
    }

    private void validate(int n) {
        if (n <= 0 || n > grid.length-1) throw new IllegalArgumentException("Out of range.");
    }

    private int getIndex(int row, int col) {
        return (row - 1) * gridSize + col;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}

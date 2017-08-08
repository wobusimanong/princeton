import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;
    private int count = 0;
    private int[] directX = {1, -1, 0, 0};
    private int[] directY = {0, 0, 1, -1};
    private int len;
    private int topIndex = 0;
    private int bottomIndex;
    
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(n + "is illegal number.");
        }
        
        len = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 1);
        bottomIndex = n * n + 1;
        grid = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            grid[0][i] = -1;
            grid[i][0] = -1;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                grid[i][j] = 0;
            }
        }
    }
    
    private void validate(int row, int col) {
        if (row < 1 || row > len) {
            throw new IllegalArgumentException("Row is out of bound.");
        }
        if (col < 1 || col > len) {
            throw new IllegalArgumentException("Column is out of bound.");
        }
    }
    
    private int xyTo1D(int row, int col) {
        return (row - 1) * len + col;
    }
    
    public void open(int row, int col) {
        validate(row, col);
        if (grid[row][col] != 1) {
            grid[row][col] = 1;
            count++;
            
            if (row == 1) {
                uf.union(xyTo1D(row, col), topIndex);
                uf2.union(xyTo1D(row, col), topIndex);
            }
            
            if (row == len) {
                uf.union(xyTo1D(row, col), bottomIndex);
            }
        
            for (int i = 0; i < 4; i++) {
                int newX = row + directX[i];
                int newY = col + directY[i];
                if (inBound(newX, newY) && grid[newX][newY] == 1) {
                    int p = xyTo1D(row, col);
                    int q = xyTo1D(newX, newY);
                    uf.union(q, p);
                    uf2.union(q, p);
                }
            }
        }
    }
    
    private boolean inBound(int x, int y) {
        return x >= 1 && x <= len && y >= 1 && y <= len;
    }
    
    public boolean isOpen(int row, int col) {
        validate(row, col);
        if (grid[row][col] == 1) {
            return true;
        }
        
        return false;
    }
    
    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf2.connected(topIndex, xyTo1D(row, col));
    }
    
    public int numberOfOpenSites() {
        return count;
    }
    
    public boolean percolates() {
        return uf.connected(topIndex, bottomIndex);
    }
    
    public static void main(String[] args) {
        Percolation perc = new Percolation(4);
        perc.open(1, 1);
        perc.open(2, 1);
        perc.open(2, 2);
        perc.open(4, 1);
        System.out.println(perc.isFull(2, 2));
    }
}
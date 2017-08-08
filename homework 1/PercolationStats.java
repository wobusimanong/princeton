import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private int n;
    private int trials;
    private double[] a;
    
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Illegal input");
        }
        
        this.n = n;
        this.trials = trials;
        a = new double[trials];
        initialize();
    }
    
    private void initialize() {
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            double openSites = 0;
            while (!perc.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                    openSites++;
                }
            }
            
            a[i] = openSites / (n * n);
        }
    }
    
    public double mean() {
        double mean = StdStats.mean(a);
        return mean;
    }
    
    public double stddev() {
        if (trials == 1) {
            return Double.NaN;
        }
        return StdStats.stddev(a);
    }
    
    public double confidenceLo() {
        double m = mean();
        double d = stddev();
        double low = m - 1.96 * d / Math.sqrt(trials);
        return low;
    }
    
    public double confidenceHi() {
        double m = mean();
        double d = stddev();
        double high = m + 1.96 * d / Math.sqrt(trials);
        return high;
    }
    
    public static void main(String[] args) {
        PercolationStats st = new PercolationStats(200, 100);
        
        System.out.println("mean = " + st.mean());
        System.out.println("stddev = " + st.stddev());
        System.out.println("95% confidence interval = " + "[" + st.confidenceLo()
                           + "," + st.confidenceHi() + "]");
    }
}
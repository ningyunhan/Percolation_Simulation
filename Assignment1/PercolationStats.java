import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.*;
public class PercolationStats{
	private double[] fraction;
	private int count;
	private Percolation res;
	public PercolationStats(int n, int trials){
		if(n <= 0 || trials <= 0){
			throw new IllegalArgumentException("not valid input");
		}
		count = trials;
		fraction = new double[trials];
		for(int i = 0; i < trials; i++){
			res = new Percolation(n);
			while(!res.percolates()){
				int column = StdRandom.uniform(1, n + 1);
				int row = StdRandom.uniform(1, n + 1);
				if(res.isOpen(column, row)){
					continue;
				}
				else{
					res.open(column, row);
				}
			}
			fraction[i] = (double)res.numberOfOpenSites() / (n * n);
		}
	}
	public double mean(){
		return StdStats.mean(fraction);
	}

	public double stddev(){
		return StdStats.stddev(fraction);
	}

	public double confidenceLo(){
		return mean() - (1.96 * stddev()) / Math.sqrt(count);
	}

	public double confidenceHi(){
		return mean() + (1.96 * stddev()) / Math.sqrt(count);
	}

	public static void main(String[] args){
		int n = StdIn.readInt();
		int trials = StdIn.readInt();
		PercolationStats test = new PercolationStats(n, trials);
		StdOut.println("mean                    = " + test.mean());
		StdOut.println("stddev                  = " + test.stddev());
		StdOut.println("95% confidence interval = [" + test.confidenceLo() + ", " + test.confidenceHi() + "]");
	}

}
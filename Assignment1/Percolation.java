import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.*;
public class Percolation{
	private int virtualtop;
	private int virtualbottom;
	private WeightedQuickUnionUF grid;
	private boolean[][] kai;
	private int temp;
	private int opensite;

	public Percolation(int n){
		if(n <= 0){
			throw new IllegalArgumentException("n must be positive");
		}
		virtualtop = 0;
		virtualbottom = n * n + 1;
		grid = new WeightedQuickUnionUF(n * n + 2);
		kai = new boolean[n][n];
		temp = n;
		opensite = 0;
	}


	public void open(int row, int col){
		if(!isValid(row, col)){
			throw new IllegalArgumentException("not vaild input row and column");
		}
		else{
			if(isOpen(row, col)){
				return;
			}
			kai[row - 1][col - 1] = true;
			opensite++;
			if(row - 1 > 0 && isOpen(row - 1, col)){
				grid.union(index(row - 1, col), index(row, col));
			}
			if(row + 1 <= temp && isOpen(row + 1, col)){
				grid.union(index(row + 1, col), index(row, col));
			}
			if(col - 1 > 0 && isOpen(row, col - 1)){
				grid.union(index(row, col -1), index(row, col));
			}
			if(col + 1 <= temp && isOpen(row, col + 1)){
				grid.union(index(row, col + 1), index(row,col));
			}
			if(row == 1){
				grid.union(virtualtop, index(row, col));
			}
			if(row == temp){
				grid.union(virtualbottom, index(row, col));
			}
		}
	}


	public boolean isOpen(int row, int col){
		if(!isValid(row, col)){
			throw new IllegalArgumentException("not vaild input row and column");
		}
		else{
			return kai[row - 1][col - 1];
		}		
	}

	public boolean isFull(int row, int col){
		if(!isValid(row, col)){
			throw new IllegalArgumentException("not vaild input row and column");
		}
		else{
			return grid.connected(virtualtop, index(row, col));
		}
	}

	public int numberOfOpenSites(){
		return opensite;
	}

	public boolean percolates(){
		return grid.connected(virtualtop, virtualbottom);
	}



	private boolean isValid(int row, int col){
		if(row >= 1 && col >= 1 && row <= temp && col <= temp){
			return true;
		}
		else{
			return false;
		}
	}

	private int index(int row, int col){
		return temp * (row - 1) + col;
	}


}

//package sparseMatrix;

import java.util.Scanner;
import java.util.ArrayList;

public class sparseMatrix<anyType> implements Matrixable<anyType>

{

	
	private ArrayList<Cell<anyType>> list;
	private int numElements;
	private int numRows, numCols;

	public sparseMatrix(int r, int c) {
		list = new ArrayList<Cell<anyType>>();
		numElements = 0;
		numRows = r;
		numCols = c;
	}

	public anyType get(int r, int c)// returns the element at row r, col c

	{
		for (int i = 0; i < list.size(); i++) {
			Cell<?> curr = list.get(i);
			if (curr.getRow() == r && curr.getCol() == c) {

				return (anyType) (curr).getValue();
			}
		}
		return null;
	}

	public anyType set(int r, int c, anyType x)// changes element at (r,c),
												// returns old value
	{
		for (int i = 0; i < list.size(); i++) {
			Cell<?> curr = list.get(i);
			if (curr.getRow() == r && curr.getCol() == c) {

				return (anyType) (list.set(i, new Cell<anyType>(x, r, c))).getValue();
			}
		}
		return null;
	}

	public void add(int r, int c, anyType x) {
		// adds obj at row r, col c
		if (r < 0 || c < 0 || r >= numRows || c >= numCols) {
			return;
		}
		numElements++;
		int key = getKey(r, c);
		for (int i = 0; i < list.size(); i++) {
			Cell<?> curr = list.get(i);
			int currKey = getKey(curr.getRow(), curr.getCol());
			if (currKey > key) {

				list.add(i, new Cell<anyType>(x, r, c));
				return;
			}
		}
		list.add(new Cell<anyType>(x, r, c));

	}

	public anyType remove(int r, int c)

	{
		int key = getKey(r, c);
		numElements--;
		for (int i = 0; i < list.size(); i++) {
			Cell<?> curr = list.get(i);
			int currKey = getKey(curr.getRow(), curr.getCol());
			if (currKey == key) {

				return (anyType) (list.remove(i)).getValue();
			}
		}
		return null;
	}

	public int size() {
		// returns # actual elements stored
		return numElements;
	}

	public int numRows()

	{
		return numRows;
		// returns # rows set in constructor
	}

	public int numColumns()

	{
		return numCols;
		// returns # cols set in constructor
	}

	private int getKey(int r, int c) {

		return r * numRows + c;

	}
	/*
	 * public boolean contains(anyType x); //true if x exists in list public
	 * int[] getLocation(anyType x); //returns location [r,c] of where x exists
	 * in list, null otherwise public Object[][] toArray(); //returns equivalent
	 * structure in 2-D array form public boolean isEmpty(); //returns true if
	 * there are no actual elements stored public void clear(); //clears all
	 * elements out of the list
	 * 
	 * public void setBlank(char blank); //allows the client to set the
	 * character that a blank spot in the array is //represented by in String
	 * toString()
	 */

	public String toString() {

		String ans = "  ";
		for (int c = 0; c < numCols; c++)
		    ans += c + " ";
		ans += "\n";
		for (int r = 0; r < numRows; r++) {
			ans += r + " ";
			for (int c = 0; c < numCols; c++) {
				anyType val = this.get(r, c);
				if (val != null)
					ans = ans + val + " ";
				else
					ans = ans + "- ";
			}
			ans = ans + "\n";

		}
		return ans;

	}

}
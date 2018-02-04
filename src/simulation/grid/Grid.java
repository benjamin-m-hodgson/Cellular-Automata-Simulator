package simulation.grid;

import java.util.*;
import simulation.cell.Cell;

public abstract class Grid {
	protected Cell[][] myCells;
	protected int myX;
	protected int myY;
	
	public Grid(int x, int y) {
		myX = x;
		myY = y;
		myCells = new Cell[myX][myY];
	}
	
	public void addCell(int x, int y, Cell cell) {
		myCells[x][y] = cell;
	}
	
	public void updateState(int x, int y, int state) {
		myCells[x][y].setState(state);
	}
	
	public void getInitialState() {
		
	}
	
	public Cell getCell(int x, int y) {
		return myCells[x][y];
	}
	
	/**
	 * 
	 * @return myCells: a 2-dimensional array containing cells for a simulation
	 */
	public Cell[][] getCells() {
		return myCells;
	}
	
	public int getXSize() {
		return this.myX;
	}
	
	public int getYSize() {
		return this.myY;
	}
	
	public void updateGrid() {
		
	}
}

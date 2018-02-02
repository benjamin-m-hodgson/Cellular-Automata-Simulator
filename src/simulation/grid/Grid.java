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
	
	public Set <Cell> getNeighbors(int x, int y) {
		Set <Cell> neighbors = new HashSet<Cell>();
		if (x == 0 && y == 0) {
			neighbors.add(this.getCell(x + 1, y));
			neighbors.add(this.getCell(x, y + 1));
			neighbors.add(this.getCell(x + 1, y + 1));
		} else if (x == 0 && y == myY - 1) {
			neighbors.add(this.getCell(x + 1, y));
			neighbors.add(this.getCell(x + 1, y - 1));
			neighbors.add(this.getCell(x, y - 1));
		} else if (x == myX && y == 0) {
			neighbors.add(this.getCell(x + 1, y));
			neighbors.add(this.getCell(x + 1, y));
			neighbors.add(this.getCell(x + 1, y));
		}
		return neighbors;
	}
	
	public void updateGrid() {
		
	}
}

package simulation.grid;
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
	
	public Cell[] getNeighbors(int x, int y) {
		Cell[] neighbors = new Cell[8];
		return neighbors;
	}
	
	public void updateGrid() {
		
	}
}

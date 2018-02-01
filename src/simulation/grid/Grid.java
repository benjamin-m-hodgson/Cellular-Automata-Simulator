package simulation.grid;
import simulation.cell.Cell;

public class Grid {
	
	private Cell[][] cells;

	public Grid(int x, int y) {
		cells = new Cell[x][y];
	}
	
	
	public void add(int x, int y, Cell c) {
		this.cells[x][y] = c;
	}
}

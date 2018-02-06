package simulation.grid;
import simulation.cell.Cell;

public abstract class Grid implements Cloneable{
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
	
	/**
	 * Returns a copy of this object using the Cloneable interface 
	 * and a deep-copy implementation
	 */
	public Grid clone() throws CloneNotSupportedException {
		Grid copyGrid = (Grid) super.clone();
		copyGrid.myX = this.myX;
		copyGrid.myY = this.myY;
		copyGrid.myCells = this.myCells.clone();
		for (int i = 0; i < copyGrid.myCells.length
				&& i < this.myCells.length; i++) {
			copyGrid.myCells[i] = this.myCells[i].clone();			
			for (int j = 0; j < copyGrid.myCells[0].length 
					&& j < this.myCells[0].length; j++) {
				copyGrid.myCells[i][j] = this.myCells[i][j].clone();
			}
		}
		return copyGrid;
	}
}

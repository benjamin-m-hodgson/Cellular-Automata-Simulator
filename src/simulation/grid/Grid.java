package simulation.grid;
import simulation.cell.Cell;

/**
 * Grid class holds cells in current simulation
 * 
 * @author Katherine Van Dyk
 * @author Ben Hodgson
 *
 */
public abstract class Grid implements Cloneable{
	protected Cell[][] myCells;
	protected int myX;
	protected int myY;
	
	/**
	 * Constructor for grid object
	 * 
	 * @param x: x size
	 * @param y: y size
	 */
	public Grid(int x, int y) {
		myX = x;
		myY = y;
		myCells = new Cell[myX][myY];
	}
	
	/**
	 * Adds cell object to its specified position on grid
	 * 
	 * @param cell: Cell object to be added
	 */
	public void addCell(Cell cell) {
		int x = cell.getX();
		int y = cell.getY();
		myCells[x][y] = cell;
	}
	
	/**
	 * Updates state of cell at position x, y
	 * 
	 * @param x: x position of cell
	 * @param y: y position of cell
	 * @param state: new state
	 */
	public void updateState(int x, int y, int state) {
		myCells[x][y].setState(state);
	}
	
	/**
	 * Gets cell object at position (x,y)
	 * 
	 * @param x: x position on grid
	 * @param y: y position on grid
	 * @return Cell object
	 */
	public Cell getCell(int x, int y) {
		return myCells[x][y];
	}
	
	/**
	 * Gets all cells in simulation
	 * 
	 * @return myCells: a 2-dimensional array containing cells for a simulation
	 */
	public Cell[][] getCells() {
		return myCells;
	}
	
	/**
	 * Returns x dimension of grid
	 * 
	 * @return size of grid in x direction
	 */
	public int getXSize() {
		return this.myX;
	}
	
	/**
	 * Returns y dimension of grid
	 * 
	 * @return size of grid in y direction
	 */
	public int getYSize() {
		return this.myY;
	}
	
	public void replace(Cell a) {
		myCells[a.getX()][a.getY()] = a;
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

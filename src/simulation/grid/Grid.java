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
    private String TYPE;

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
     * @return myCells: a 2-dimensional array containing cells for a simulation
     */
    public Cell[][] getCells() {
	return myCells;
    }

    /**
     * @return size of grid in x direction
     */
    public int getXSize() {
	return this.myX;
    }

    /**
     * @return y dimension of grid
     */
    public int getYSize() {
	return this.myY;
    }

    /**
     * Sets type of simulation grid represents to string @param s
     */
    public void setType(String s) {
	this.TYPE = s;
    }
    
    /**
     * @return type of simulation grid represents
     */
    public String getType() {
	return this.TYPE;
    }
    
    /**
     * Update state attribute for all cells in grid
     * 
     * @param g: Current grid
     */
    public void updateStates() {
	for(Cell[] row : this.getCells()) {
	    for(Cell cell : row) {
		cell.updateState();
		cell.setMove(false);
	    }
	}
    }

    /**
     * Gets cell count of particular state for graph data points 
     * 
     * @param g: current grid
     * @param state: cell state to count frequency of
     * @return int: representing number of cells on grid that have input state
     */
    public int stateCount(int state) {
	int count = 0;
	for(Cell[] row : this.getCells()) {
	    for(Cell cell : row) {
		if(cell.getState() == state) count++;
	    }
	}
	return count;
    }
}

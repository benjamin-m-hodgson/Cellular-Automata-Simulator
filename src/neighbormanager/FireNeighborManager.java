package neighbormanager;
import java.util.ArrayList;
import java.util.Arrays;
import simulation.cell.*;
import simulation.grid.*;

/**
 * Manages neighbors of current fire grid 
 * 
 * @author Katherine Van Dyk
 *
 */
public class FireNeighborManager extends NeighborManager {

	private int BURNING = 2;

	/**
	 * Returns number of cell's burning neighbors
	 * 
	 * @param c: Cell c
	 * @param g: Current grid g
	 * @return
	 */
	public int neighborCount(FireCell c, Grid g) {
		for(Cell neighbor : getNeighbors(c, g)) {
			if(neighbor.getState() == BURNING) {
				return 1;
			}
		}
		return 0;
	}

	/**
	 * Returns all neighbors of Cell c
	 * 
	 * @param c
	 * @param g
	 * @return
	 */
	private Cell[] getNeighbors(Cell c, Grid g) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		neighbors.addAll(Arrays.asList(NSEWCells(c , g)));
		neighbors.addAll(Arrays.asList(diagonalCells(c ,g)));
		return neighbors.toArray(new Cell[neighbors.size()]);
	}
	
}

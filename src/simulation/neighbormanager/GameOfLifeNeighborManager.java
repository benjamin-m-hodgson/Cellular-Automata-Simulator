package simulation.neighbormanager;
import simulation.cell.*;
import simulation.grid.*;

/**
 * Manages neighbors of current Game of Life grid
 * 
 * @author Katherine Van Dyk
 *
 */
public class GameOfLifeNeighborManager extends NeighborManager {
	
	private static final int LIVE = 0;

	/**
	 * Return count of live neighbor cells
	 * 
	 * @param neighbors: Arraylist of all cell's neighbors 
	 * @return
	 */
	public int neighborCount(GameOfLifeCell c, Grid g) {
		Cell[] neighbors = getNeighbors(c, g);
		int count = 0;
		for(Cell neighbor : neighbors) {
			if(neighbor.getState() == LIVE) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Gets all neighbors of current cell c
	 * 
	 * @param c
	 * @param g
	 * @return
	 */
	private Cell[] getNeighbors(Cell c, Grid g){
		return NSEWCells(c ,g);
	}
	
}

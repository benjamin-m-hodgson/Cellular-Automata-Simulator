package simulation.ruleSet.neighborManager;
import java.util.ArrayList;
import java.util.Arrays;
import simulation.cell.*;
import simulation.grid.*;
import simulation.neighborhoods.*;

/**
 * Manages neighbors of current fire grid 
 * 
 * @author Katherine Van Dyk
 *
 */
public class FireNeighborManager extends NeighborManager {
	private Neighborhood NEIGHBORHOOD;
	
	public FireNeighborManager(String CellType) {
		if(CellType.equals("Square")) NEIGHBORHOOD = new SquareNeighborhood();
 	}

	/**
	 * Returns number of cell's burning neighbors
	 * 
	 * @param c: Cell c
	 * @param g: Current grid g
	 * @return
	 */
	public int neighborCount(Cell c, Grid g, int param) {
		for(Cell neighbor : getNeighbors(c, g)) {
			if(neighbor.getState() == param) {
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
	@Override
	protected Cell[] getNeighbors(Cell c, Grid g) {
		ArrayList<Cell> neighbors = new ArrayList<>();
		neighbors.addAll(Arrays.asList(NEIGHBORHOOD.NSEWCells(c , g)));
		neighbors.addAll(Arrays.asList(NEIGHBORHOOD.diagonalCells(c ,g)));
		return neighbors.toArray(new Cell[neighbors.size()]);
	}
	
}

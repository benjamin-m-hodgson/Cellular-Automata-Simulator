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

	public FireNeighborManager(Neighborhood n, boolean finite) {
		super(n, finite);
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
		if(FINITE) {
			neighbors.addAll(Arrays.asList(NEIGHBORHOOD.FiniteCardinal(c , g)));
			neighbors.addAll(Arrays.asList(NEIGHBORHOOD.FiniteDiagonal(c ,g)));
		}
		else {
			neighbors.addAll(Arrays.asList(NEIGHBORHOOD.TorodialCardinal(c , g)));
			neighbors.addAll(Arrays.asList(NEIGHBORHOOD.TorodialDiagonal(c ,g)));
		}
		return neighbors.toArray(new Cell[neighbors.size()]);
	}

}

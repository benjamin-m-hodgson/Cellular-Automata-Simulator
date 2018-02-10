package simulation.ruleSet.neighborManager;
import simulation.cell.*;
import simulation.grid.*;
import simulation.neighborhoods.Neighborhood;

/**
 * Manages neighbors of current Game of Life grid
 * 
 * @author Katherine Van Dyk
 *
 */
public class GameOfLifeNeighborManager extends NeighborManager {

	public GameOfLifeNeighborManager(Neighborhood n, boolean finite) {
		super(n, finite);
 	}
	
	
	/**
	 * Gets all neighbors of current cell c
	 * 
	 * @param c
	 * @param g
	 * @return
	 */
	@Override
	protected Cell[] getNeighbors(Cell c, Grid g){
		if(FINITE) {
			return NEIGHBORHOOD.FiniteCardinal(c ,g);
		}
		else {
			return NEIGHBORHOOD.TorodialCardinal(c, g);
		}
	}
	
}

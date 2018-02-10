package simulation.ruleSet.neighborManager;
import simulation.cell.*;
import simulation.grid.*;
import simulation.neighborhoods.Neighborhood;
import simulation.neighborhoods.SquareNeighborhood;

/**
 * Manages neighbors of current Game of Life grid
 * 
 * @author Katherine Van Dyk
 *
 */
public class GameOfLifeNeighborManager extends NeighborManager {
	
	private Neighborhood NEIGHBORHOOD;

	public GameOfLifeNeighborManager(String CellType) {
		if(CellType.equals("Square")) NEIGHBORHOOD = new SquareNeighborhood();
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
		return NEIGHBORHOOD.NSEWCells(c ,g);
	}
	
}

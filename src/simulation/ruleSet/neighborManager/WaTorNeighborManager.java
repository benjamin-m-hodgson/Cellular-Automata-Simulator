package simulation.ruleSet.neighborManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import simulation.cell.*;
import simulation.grid.*;
import simulation.neighborhoods.Neighborhood;
import simulation.neighborhoods.SquareNeighborhood;

/**
 * Manages neighbors of current WaTor Grid
 * 
 * @author Katherine Van Dyk
 *
 */
public class WaTorNeighborManager extends NeighborManager {

	private int FISH = 0;
	private int VACANT = 2;
	private Neighborhood NEIGHBORHOOD;

	
	public WaTorNeighborManager(String CellType) {
		if(CellType.equals("Square")) NEIGHBORHOOD = new SquareNeighborhood();
 	}
	
	/**
	 * Returns a fish or cell neighbor of Cell c
	 * 
	 * @param c
	 * @param g
	 * @return
	 */
	public Cell vacantOrFishNeighbor(WaTorCell c, Grid g) {
		Random rand = new Random();
		ArrayList<Cell> freeNeighbors = new ArrayList<>();		
		freeNeighbors.addAll(Arrays.asList(getNeighbor(c , g, VACANT)));
		freeNeighbors.addAll(Arrays.asList(getNeighbor(c , g, FISH)));

		Cell[] cells = freeNeighbors.toArray(new WaTorCell[freeNeighbors.size()]);
		if(cells.length == 0) return null;
		else{
			return cells[rand.nextInt(cells.length)];
		}
	}

	/**
	 * Returns all neighbors of cell c
	 * 
	 * @param c
	 * @param g
	 * @return
	 */
	@Override
	protected Cell[] getNeighbors(Cell c, Grid g) {
		ArrayList<Cell> neighbors = new ArrayList<>();
		neighbors.addAll(Arrays.asList(NEIGHBORHOOD.NSEWCells(c , g)));
		return neighbors.toArray(new Cell[neighbors.size()]);
	}

}

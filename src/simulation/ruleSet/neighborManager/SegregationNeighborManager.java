package simulation.ruleSet.neighborManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import simulation.cell.*;
import simulation.grid.*;
import simulation.neighborhoods.Neighborhood;

/**
 * Manages neighbors of current segregation grid 
 * 
 * @author Katherine Van Dyk
 *
 */
public class SegregationNeighborManager extends NeighborManager {

	private int VACANT = 2; 
	private Neighborhood NEIGHBORHOOD;

	public SegregationNeighborManager(Neighborhood n, boolean finite) {
		super(n, finite);
 	}
	
	
	/**
	 * Returns a random vacant cell
	 * 
	 * @param neighbors: Arraylist of all cell's neighbors 
	 * @return
	 */
	public SegregationCell findVacantCell(Grid g) {
		ArrayList<SegregationCell> vacant = new ArrayList<>();
		Random rand = new Random();
		for(Cell[] row : g.getCells()) {
			for(Cell cell : row) {
				SegregationCell sCell = (SegregationCell) cell;
				if(!sCell.getMove() && sCell.getState() == VACANT) {
					vacant.add(sCell);
				}
			}
		}
		if(!vacant.isEmpty()) {
			return vacant.get(rand.nextInt(vacant.size()));
		}
		else {
			return null;
		}
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
		ArrayList<Cell> agents = new ArrayList<>();
		
		if(FINITE) {
			neighbors.addAll(Arrays.asList(NEIGHBORHOOD.FiniteCardinal(c , g)));
			neighbors.addAll(Arrays.asList(NEIGHBORHOOD.FiniteDiagonal(c ,g)));
		}
		else {
			neighbors.addAll(Arrays.asList(NEIGHBORHOOD.TorodialCardinal(c , g)));
			neighbors.addAll(Arrays.asList(NEIGHBORHOOD.TorodialDiagonal(c ,g)));
		}

		for(Cell neighbor: neighbors) {
			if(neighbor.getState() != VACANT) {
				agents.add(neighbor);
			}
		}
		return agents.toArray(new Cell[agents.size()]);
	}

	/**
	 * Returns number of cells in same group
	 * 
	 * @param neighbors
	 * @param c
	 * @return
	 */
	private int neighborCount(Cell c, Grid g) {
		Cell[] neighbors = getNeighbors(c, g);
		int count = 0;
		for(Cell neighbor : neighbors) {
			if(neighbor.getState() == c.getState()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns satisfaction level of cell object
	 * 
	 * @param c: cell object whose satisfaction is being evaluated
	 * @param g: current grid state
	 * @return double representing cell satisfaction
	 */
	public double getNeighborSatisfaction(Cell c, Grid g) {
		return (double) neighborCount(c, g) / getNeighbors(c, g).length;
	}

}

package simulation.ruleSet.neighborManager;
import java.util.ArrayList;
import java.util.Random;

import simulation.cell.Cell;
import simulation.grid.Grid;
import simulation.neighborhoods.Neighborhood;

/**
 * Gets neighbors in certain positions/configurations
 * 
 * @author Katherine Van Dyk
 *
 */
public abstract class NeighborManager {
	
	protected Neighborhood NEIGHBORHOOD;
	protected boolean FINITE;
	
	public NeighborManager(Neighborhood n, boolean finite) {
		this.NEIGHBORHOOD = n;
		this.FINITE = finite;
 	}
 
	protected abstract Cell[] getNeighbors(Cell c, Grid g);


	/**
	 * Return count of live neighbor cells
	 * 
	 * @param neighbors: Arraylist of all cell's neighbors 
	 * @return
	 */
	public int neighborCount(Cell c, Grid g, int param) {
		Cell[] neighbors = getNeighbors(c, g);
		int count = 0;
		for(Cell neighbor : neighbors) {
			if(neighbor.getState() == param && !neighbor.getMove()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Return count of live neighbor cells
	 * 
	 * @param neighbors: Arraylist of all cell's neighbors 
	 * @return
	 */
	public Cell getNeighbor(Cell c, Grid g, int param) {
		Random rand = new Random();
		ArrayList<Cell> freeNeighbors = new ArrayList<>();		
		
		for(Cell neighbor : getNeighbors(c, g)) {
			if(neighbor.getState() == param && !neighbor.getMove()) {
				freeNeighbors.add(neighbor);
			}
		}
		
		Cell[] cells =  freeNeighbors.toArray(new Cell[freeNeighbors.size()]);
		if(cells.length == 0) return null;
		return cells[rand.nextInt(cells.length)];
	}



}

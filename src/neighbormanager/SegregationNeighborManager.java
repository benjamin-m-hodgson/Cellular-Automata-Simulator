package neighbormanager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import simulation.cell.*;
import simulation.grid.*;

public class SegregationNeighborManager extends NeighborManager {

	private int GROUP1 = 0;
	private int GROUP2 = 1;
	private int VACANT = 2; 

	/**
	 * Returns a random vacant cell
	 * 
	 * @param neighbors: Arraylist of all cell's neighbors 
	 * @return
	 */
	public SegregationCell findVacantCell(Grid g) {
		ArrayList<SegregationCell> vacant = new ArrayList<SegregationCell>();
		Random rand = new Random();
		for(SegregationCell[] row : (SegregationCell[][]) g.getCells()) {
			for(SegregationCell cell : row) {
				if(!cell.getMove() && cell.getState() == VACANT) vacant.add(cell);
			}
		}
		if(vacant.size() > 0) return vacant.get(rand.nextInt(vacant.size()));
		else return null;
	}

	/**
	 * Returns all neighbors of Cell c
	 * 
	 * @param c
	 * @param g
	 * @return
	 */
	private SegregationCell[] getNeighbors(SegregationCell c, Grid g) {
		ArrayList<SegregationCell> neighbors = new ArrayList<SegregationCell>();
		neighbors.addAll(Arrays.asList( (SegregationCell[]) NSEWCells(c , g)));
		neighbors.addAll(Arrays.asList( (SegregationCell[]) diagonalCells(c ,g)));

		return neighbors.toArray(new SegregationCell[neighbors.size()]);
	}

	/**
	 * Returns number of cells in opposite group
	 * 
	 * @param neighbors
	 * @param c
	 * @return
	 */
	private int neighborCount(SegregationCell c, Grid g) {
		SegregationCell[] neighbors = getNeighbors(c, g);
		int count = 0;
		int cellState = c.getState();
		int oppositeState;

		if(cellState == GROUP1) oppositeState = GROUP2;
		else oppositeState = GROUP1;

		for(Cell neighbor : neighbors) {
			if(neighbor.getState() == oppositeState) count++;
		}
		return count;
	}

	public double getNeighborSatisfaction(SegregationCell c, Grid g) {
		return (double) neighborCount(c, g) / getNeighbors(c, g).length;
	}

}

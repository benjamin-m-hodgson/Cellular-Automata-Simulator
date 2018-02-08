package simulation.neighbormanager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import simulation.cell.*;
import simulation.grid.*;

/**
 * Manages neighbors of current WaTor Grid
 * 
 * @author Katherine Van Dyk
 *
 */
public class WaTorNeighborManager extends NeighborManager {

	private int FISH = 0;
	private int VACANT = 2;

	/**
	 * Returns a vacant neighbor of cell c
	 * 
	 * @param c
	 * @param g
	 * @return
	 */
	public WaTorCell vacantNeighbor(WaTorCell c, Grid g) {
		Random rand = new Random();
		ArrayList<WaTorCell> freeNeighbors = new ArrayList<>();		
		for(Cell neighbor : getNeighbors(c, g)) {
			WaTorCell wNeighbor = (WaTorCell) neighbor;
			if(wNeighbor.getState() == VACANT && !wNeighbor.getMove()) {
				freeNeighbors.add(wNeighbor);
			}
		}
		WaTorCell[] cells =  freeNeighbors.toArray(new WaTorCell[freeNeighbors.size()]);
		if(cells.length == 0) {
			return null;
		}
		else if(cells.length == 1) {
			return cells[0];
		}
		else {
			return cells[rand.nextInt(cells.length)];
		}
	}

	/**
	 * Returns a fish or cell neighbor of Cell c
	 * 
	 * @param c
	 * @param g
	 * @return
	 */
	public WaTorCell vacantOrFishNeighbor(WaTorCell c, Grid g) {
		Random rand = new Random();
		ArrayList<WaTorCell> freeNeighbors = new ArrayList<>();		
		for(Cell neighbor : getNeighbors(c, g)) {
			WaTorCell wNeighbor = (WaTorCell) neighbor;
			if((wNeighbor.getState() == VACANT || wNeighbor.getState() == FISH)
					&& !wNeighbor.getMove()) {
				freeNeighbors.add(wNeighbor);
			}
		}

		WaTorCell[] cells =  freeNeighbors.toArray(new WaTorCell[freeNeighbors.size()]);
		if(cells.length == 0) {
			return null;
		}
		else if(cells.length == 1) {
			return cells[0];
		}
		else {
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
	private Cell[] getNeighbors(WaTorCell c, Grid g) {
		ArrayList<Cell> neighbors = new ArrayList<>();
		neighbors.addAll(Arrays.asList(NSEWCells(c , g)));
		neighbors.addAll(Arrays.asList(diagonalCells(c ,g)));

		return neighbors.toArray(new Cell[neighbors.size()]);
	}

}

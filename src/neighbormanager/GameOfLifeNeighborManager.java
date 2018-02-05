package neighbormanager;
import simulation.cell.*;
import simulation.grid.*;

public class GameOfLifeNeighborManager extends NeighborManager {
	
	private static final int LIVE = 0;

	/**
	 * Return count of live neighbor cells
	 * 
	 * @param neighbors: Arraylist of all cell's neighbors 
	 * @return
	 */
	public int neighborCount(GameOfLifeCell c, Grid g) {
		GameOfLifeCell[] neighbors = getNeighbors(c, g);
		int count = 0;
		for(Cell neighbor : neighbors) {
			if(neighbor.getState() == LIVE) {
				count++;
			}
		}
		return count;
	}
	
	
	private GameOfLifeCell[] getNeighbors(GameOfLifeCell c, Grid g){
		return (GameOfLifeCell[]) NSEWCells(c ,g);
	}
	
}

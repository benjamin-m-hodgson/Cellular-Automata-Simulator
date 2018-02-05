package simulation.ruleSet;
import java.util.Random;

import neighbormanager.FireNeighborManager;
import simulation.cell.*;
import simulation.grid.*;

/**
 * RULES: 
 * Tree Cell: If tree surrounding it burns, and rand # < probCatch -> burn
 * Burning Cell: Always burning
 * 
 *  @param Cell c: cell whose state is being evaluated
 *  @param Cell[] neighbors: neighbors of c
 */
public class FireRuleset implements Ruleset {
	
	private int BURNING = 0;
	private int TREE = 1;
	private Grid GRID;
	private FireNeighborManager NEIGHBOR_MANAGER;
	double PROBCATCH;
	
	/**
	 * Sets initial parameters for current simulation
	 * 
	 * @param probCatch
	 */
	public FireRuleset(double probCatch) {
		this.PROBCATCH = probCatch;
		this.NEIGHBOR_MANAGER = new FireNeighborManager();
	}
	
	/**
	 * Sets current simulation grid 
	 */
	@Override
	public void setGrid(Grid g) {
		GRID = g;
	}
	
	/**
	 * Processes all cells in current simulation
	 */
	@Override
	public void processCells() {
		for(int r = 0; r < GRID.getXSize(); r++) {
			for(int c = 0; c < GRID.getYSize(); c++) {
				Cell cell = GRID.getCell(r, c);
				int newState = processCell((FireCell) cell);
				cell.setState(newState);
			}
		}
	}

	/**
	 * Processes single cell in grid
	 * 
	 * @param c: cell to be processed
	 * @return int: new state
	 */
	public int processCell(FireCell c) {
		Random rand = new Random();
		if(c.getState() == BURNING) {
			return BURNING;
		}
		else if(NEIGHBOR_MANAGER.neighborCount(c, GRID) > 0 && rand.nextDouble() < this.PROBCATCH) {
			return BURNING;
		}
		else {
			return TREE;
		}
	}

}

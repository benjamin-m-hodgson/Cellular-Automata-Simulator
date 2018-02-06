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
 *  @author Katherine Van Dyk
 *  @author Ben Hodgson
 */
public class FireRuleset implements Ruleset {
	
	private int VACANT = 0;
	private int TREE = 1;
	private int BURNING = 2;
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
		updateStates();
	}

	/**
	 * Processes single cell in grid
	 * 
	 * @param c: cell to be processed
	 * @return int: new state
	 */
	public int processCell(FireCell c) {
		Random rand = new Random();
		if(NEIGHBOR_MANAGER.neighborCount(c, GRID) > 0 
				&& rand.nextDouble() < this.PROBCATCH
				&& c.getState() != VACANT) {
			return BURNING;
		}
		else if (c.getState() == TREE) {
			return TREE;
		} 
		else {
			return VACANT;
		}
	}
	
	/**
	 * Updates states for all cells at once
	 */
	public void updateStates() {
		for(int r = 0; r < GRID.getXSize(); r++) {
			for(int c = 0; c < GRID.getYSize(); c++) {
				FireCell cell = (FireCell) GRID.getCell(r, c);
				cell.updateState();
			}
		}
	}
}

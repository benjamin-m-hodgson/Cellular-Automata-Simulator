package simulation.ruleSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import simulation.cell.*;
import simulation.grid.*;

public class FireRuleset implements Ruleset {
	
	int BURNING = 0;
	int TREE = 1;
	
	double PROBCATCH;
	
	public FireRuleset(double probCatch) {
		this.PROBCATCH = probCatch;
	}

	/**
	 * RULES: 
	 * Tree Cell: If tree surrounding it burns, and rand # < probCatch -> burn
	 * Burning Cell: Always burning
	 * 
	 *  @param Cell c: cell whose state is being evaluated
	 *  @param Cell[] neighbors: neighbors of c
	 */
	public int processCell(Cell c, Cell[] neighbors) {
		Random rand = new Random();
		if(c.getState() == BURNING) {
			return BURNING;
		}
		else if(neighborCount(neighbors) > 0 && rand.nextDouble() < this.PROBCATCH) {
			return BURNING;
		}
		else {
			return TREE;
		}
	}


	public int neighborCount(Cell[] neighbors) {
		for(Cell neighbor : neighbors) {
			if(neighbor.getState() == BURNING) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public Cell[] getNeighbors(Cell c, Grid g) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		NeighborManager nm = new NeighborManager();
		neighbors.addAll(Arrays.asList(nm.NSEWCells(c ,g)));
		neighbors.addAll(Arrays.asList(nm.diagonalCells(c ,g)));
		Cell[] retNeighbors = neighbors.toArray(new Cell[neighbors.size()]);
		return retNeighbors;
	}

	@Override
	public void processCells(Grid g) {
		for(int r = 0; r < g.getXSize(); r++) {
			for(int c = 0; c < g.getYSize(); r++) {
				Cell cell = g.getCell(r, c);
				int newState = processCell(cell, getNeighbors(cell, g));
				cell.setState(newState);
			}
		}
	}

}

package simulation.ruleSet;
import java.util.ArrayList;
import java.util.Arrays;

import simulation.cell.*;
import simulation.grid.Grid;

public class GameOfLifeRuleset implements Ruleset {
	private int MAXLIFE;
	private int MINLIFE;
	private int BIRTH;

	private static final int LIVE = 1;
	private static final int DEAD = 0;

	public GameOfLifeRuleset(int minLife, int maxLife, int birth) {
		this.MINLIFE = minLife;
		this.MAXLIFE = maxLife;
		this.BIRTH = birth;
	}

	/**
	 * RULES: 
	 * Live Cell: < MINLIFE or > MAXLIFE neighbors -> cell dies
	 * Dead Cell: Exactly BIRTH neighbors -> cell comes back to life
	 * 
	 *  @param Cell c: cell whose state is being evaluated
	 *  @param Cell[] neighbors: neighbors of c
	 */

	public int processCell(Cell c, Cell[] neighbors) {
		int liveCount = neighborCount(neighbors);
		if(c.getState() == LIVE) {
			if(liveCount >= this.MINLIFE || liveCount <= this.MAXLIFE) return LIVE;
			else return DEAD;
		}
		else {
			if(liveCount == BIRTH) return LIVE;
			else return DEAD;
		}
	}


	public int neighborCount(Cell[] neighbors) {
		int count = 0;
		for(Cell neighbor : neighbors) {
			if(neighbor.getState() == LIVE) {
				count++;
			}
		}
		return count;
	}

	@Override
	public Cell[] getNeighbors(Cell c, Grid g) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		NeighborManager nm = new NeighborManager();
		neighbors.addAll(Arrays.asList(nm.NSEWCells(c ,g)));

		return (Cell[]) neighbors.toArray();
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
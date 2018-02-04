package simulation.ruleSet;
import java.util.ArrayList;
import java.util.Arrays;

import simulation.cell.*;
import simulation.grid.Grid;

public class SegregationRuleset implements Ruleset {

	private int GROUP1 = 0;
	private int GROUP2 = 1;
	private int VACANT = 2;

	double TOLERANCE;

	/**
	 * RULES: 
	 * Satisfied Cell: % neighbors satisfied < tolerance -> Dissatisfied
	 * Dissatisfied Cell: 
	 * 
	 *  @param Cell c: cell whose state is being evaluated
	 *  @param Cell[] neighbors: neighbors of c
	 */
	public SegregationRuleset(double tolerance) {
		this.TOLERANCE = tolerance;
	}

	private void moveCell(Cell c, Grid g) {
		SegregationCell newCell = findVacantCell(g);
		newCell.setState(c.getState());
		newCell.setMove(true);
		c.setState(VACANT);
	}

	private SegregationCell findVacantCell(Grid g) {
		for(int r = 0; r < g.getXSize(); r++) {
			for(int c = 0; c < g.getYSize(); r++) {
				SegregationCell cell = (SegregationCell) g.getCell(r, c);
				if(!cell.getMove() && cell.getState() == VACANT) {
					return cell;
				}
			}
		}
		return null;
	}


	/**
	 * Returns number of cells in group 1
	 */
	
	public int neighborCount(Cell[] neighbors, Cell c) {
		int count = 0;
		int cellState = c.getState();
		int oppositeState;
		if(cellState == GROUP1) oppositeState = GROUP2;
		else oppositeState = GROUP1;
		SegregationCell[] sNeighbors = (SegregationCell[]) neighbors;
		for(SegregationCell neighbor : sNeighbors) {
			if(neighbor.getState() == oppositeState) count++;
		}
		return count;
	}

	private double getSatisfaction(Cell c, Cell[] neighbors) {
		return neighborCount(neighbors, c) / neighbors.length;
	}

	@Override
	public Cell[] getNeighbors(Cell c, Grid g) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		NeighborManager nm = new NeighborManager();
		neighbors.addAll(Arrays.asList(nm.NSEWCells(c ,g)));
		neighbors.addAll(Arrays.asList(nm.diagonalCells(c ,g)));

		return (Cell[]) neighbors.toArray();
	}

	@Override
	public void processCells(Grid g) {
		for(int r = 0; r < g.getXSize(); r++) {
			for(int c = 0; c < g.getYSize(); r++) {
				Cell cell = g.getCell(r, c);
				if(cell.getState() == VACANT) continue;
				else if(getSatisfaction(cell, getNeighbors(cell, g)) < TOLERANCE) {
					moveCell(cell, g);
				}
			}
		}
	}


}


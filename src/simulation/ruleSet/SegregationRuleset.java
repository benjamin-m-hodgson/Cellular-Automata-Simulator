package simulation.ruleSet;
import java.util.ArrayList;
import java.util.Arrays;

import simulation.cell.*;
import simulation.grid.Grid;

public class SegregationRuleset implements Ruleset {
	
	int DISSATISFIED = 0;
	int SATISFIED = 1;
	
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

	@Override
	public int processCell(Cell c, Cell[] neighbors) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int neighborCount(Cell[] neighbors) {
		int count = 0;
		for(Cell neighbor : neighbors) {
			if(neighbor.getState() == SATISFIED) {
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
		neighbors.addAll(Arrays.asList(nm.diagonalCells(c ,g)));
		
		return (Cell[]) neighbors.toArray();
	}

}

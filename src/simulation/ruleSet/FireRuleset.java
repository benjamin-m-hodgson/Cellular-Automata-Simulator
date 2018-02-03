package simulation.ruleSet;
import java.util.ArrayList;
import java.util.Arrays;

import simulation.cell.*;
import simulation.grid.*;

public class FireRuleset implements Ruleset {
	
	public FireRuleset(double probCatch) {
		
	}

	@Override
	public int processCell(Cell c, Cell[] neighbors) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int neighborCount(Cell[] neighbors) {
		// TODO Auto-generated method stub
		return 0;
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

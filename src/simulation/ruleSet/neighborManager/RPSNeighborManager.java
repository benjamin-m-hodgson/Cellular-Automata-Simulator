package simulation.ruleSet.neighborManager;

import java.util.ArrayList;
import java.util.Arrays;
import simulation.cell.Cell;
import simulation.grid.Grid;
import simulation.neighborhoods.*;

public class RPSNeighborManager extends NeighborManager {
	
	private Neighborhood NEIGHBORHOOD;
	
	public Cell[] getNeighbors(Cell c, Grid g) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		neighbors.addAll(Arrays.asList(NEIGHBORHOOD.NSEWCells(c , g)));
		neighbors.addAll(Arrays.asList(NEIGHBORHOOD.diagonalCells(c ,g)));
		return neighbors.toArray(new Cell[neighbors.size()]);
	}

}

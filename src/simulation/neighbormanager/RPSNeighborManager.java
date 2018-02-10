package simulation.neighbormanager;

import java.util.ArrayList;
import java.util.Arrays;

import simulation.cell.Cell;
import simulation.grid.Grid;

public class RPSNeighborManager extends NeighborManager {
	
	public Cell[] getNeighbors(Cell c, Grid g) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		neighbors.addAll(Arrays.asList(NSEWCells(c , g)));
		neighbors.addAll(Arrays.asList(diagonalCells(c ,g)));
		return neighbors.toArray(new Cell[neighbors.size()]);
	}

}

package simulation.ruleSet.neighborManager;

import java.util.ArrayList;
import java.util.Arrays;
import simulation.cell.Cell;
import simulation.grid.Grid;
import simulation.neighborhoods.*;

public class RPSNeighborManager extends NeighborManager {

    public RPSNeighborManager(Neighborhood n, boolean finite) {
	super(n, finite);
    }

    public Cell[] getNeighbors(Cell c, Grid g) {
	ArrayList<Cell> neighbors = new ArrayList<Cell>();
	if(FINITE) {
	    neighbors.addAll(Arrays.asList(NEIGHBORHOOD.FiniteCardinal(c , g)));
	    neighbors.addAll(Arrays.asList(NEIGHBORHOOD.FiniteDiagonal(c ,g)));
	}
	else {
	    neighbors.addAll(Arrays.asList(NEIGHBORHOOD.TorodialCardinal(c , g)));
	    neighbors.addAll(Arrays.asList(NEIGHBORHOOD.TorodialDiagonal(c ,g))); 
	}
	return neighbors.toArray(new Cell[neighbors.size()]);
    }

}

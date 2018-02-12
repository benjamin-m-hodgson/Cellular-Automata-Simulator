package simulation.ruleSet.neighborManager;

import java.util.ArrayList;
import simulation.cell.Cell;
import simulation.grid.Grid;
import simulation.neighborhoods.Neighborhood;

/**
 * 
 * Fetches sets of neighbors for Sugar Cells
 * @author Michael Acker
 * @date 2/9/18
 *
 */
public class SugarNeighborManager extends NeighborManager {
    private int VISION;

    /**
     * Constructor
     * @param n
     * @param finite
     */
    public SugarNeighborManager(Neighborhood n, boolean finite) {
	super(n, finite);
    }

    public Cell[] getNeighbors(Cell c, Grid g, int vision){
	this.VISION = vision;
	return getNeighbors(c, g);
    }

    /**
     * Fetches neighbors in cardinal directions up to a distance vision
     */
    @Override
    protected Cell[] getNeighbors(Cell c, Grid g) {
	ArrayList<Cell> neighborsList = new ArrayList<Cell>();
	int x = c.getX();
	int y = c.getY();
	for (int k = 1; k <= VISION; k++) {
	    if(x + k < g.getXSize()) {
		neighborsList.add(g.getCell(x + k, y));
	    }
	    if(x - k >= 0) {
		neighborsList.add(g.getCell(x - 1, y));
	    }
	    if(y + k < g.getYSize()) {
		neighborsList.add(g.getCell(x, y + 1));
	    }
	    if(y - k >= 0) {
		neighborsList.add(g.getCell(x, y - 1));
	    }
	}
	Cell[] neighbors = neighborsList.toArray(new Cell[neighborsList.size()]);
	return neighbors;
    }

}

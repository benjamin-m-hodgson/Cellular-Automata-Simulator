package simulation.neighborhoods.neighborhoodFactories;

import java.util.ArrayList;
import java.util.List;

import simulation.cell.Cell;
import simulation.grid.Grid;

public class NeighborhoodFactory {

    /**
     * Gets cardinal cells assuming out of bounds in x/y directions
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Cardinal neighbors if out of bounds on finite grid
     */
    public List<Cell> cardinalFiniteCheck(Cell c, Grid g){	
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>();
	
	if(x + 1 < g.getXSize()) {
	    neighbors.add(g.getCell(x + 1, y));
	}
	if(x - 1 >= 0) {
	    neighbors.add(g.getCell(x - 1, y));
	}
	if(y + 1 < g.getYSize()) {
	    neighbors.add(g.getCell(x, y + 1));
	}
	if(y - 1 >= 0) {
	    neighbors.add(g.getCell(x, y - 1));
	}
	return neighbors;
    }

    /**
     * Gets cardinal cells assuming out of bounds in x/y directions
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Cardinal neighbors if out of bounds on finite grid
     */
    public List<Cell> cardinalWrapCheck(Cell c, Grid g){	
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>();
	if(x == 0) {
	    neighbors.add(g.getCell(g.getXSize() - 1, y));
	}
	if(x == g.getXSize() - 1) {
	    neighbors.add(g.getCell(0, y));
	}
	if(y == 0) {
	    neighbors.add(g.getCell(x, g.getYSize() - 1));
	}
	if(y == g.getYSize() - 1) {
	    neighbors.add(g.getCell(0, y));
	}
	return neighbors;
    }

    
    /**
     * Returns wrapped neighbors that violate x-bounds
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Diagonal neighbors if wrappable in x-direction
     */
    public List<Cell> diagonalFiniteCheck(Cell c, Grid g) {
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>();

	if(x < g.getXSize() - 1 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(x + 1, y + 1));
	}
	if(x > 0 && y > 0) {
	    neighbors.add(g.getCell(x - 1, y - 1));
	}
	if(x > 0 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(x - 1, y + 1));
	}
	if(x < g.getXSize() - 1 && y > 0) {
	    neighbors.add(g.getCell(x + 1, y - 1));
	}
	return neighbors;
    }
    
    
    /**
     * Returns wrapped diagonal neighbors that violate x-bounds
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Diagonal neighbors if wrappable in x-direction
     */
    public List<Cell> xBoundCheck(Cell c, Grid g) {
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>(); 

	if(x == 0 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(g.getXSize() -1, y + 1));
	}
	if(x == 0 && y > 0) {
	    neighbors.add(g.getCell(g.getXSize() -1, y -1));
	}
	if(x == g.getXSize() - 1 && y > 0) {
	    neighbors.add(g.getCell(0, y - 1));
	}
	if(x == g.getXSize() - 1 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(0, y + 1));
	}
	return neighbors;
    }

    /**
     * Returns wrapped diagonal neighbors that violate y-bounds
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Diagonal neighbors if wrappable in y-direction
     */
    public List<Cell> yBoundCheck(Cell c, Grid g) {
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>(); 

	if(x < g.getXSize() -1 && y == 0) {
	    neighbors.add(g.getCell(x + 1, g.getYSize() -1));
	}
	if(x > 0 && y == 0) {
	    neighbors.add(g.getCell(x - 1, g.getYSize() -1));
	}
	if(x < g.getXSize() - 1 && y == g.getYSize() - 1) {
	    neighbors.add(g.getCell(x + 1, 0));
	}
	if(x > 0 && y == g.getYSize() - 1) {
	    neighbors.add(g.getCell(x - 1, 0));
	}
	return neighbors;
    }

    /**
     * Returns wrapped diagonal neighbors that violate x-bounds and y-bounds
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Diagonal neighbors if wrappable in x-direction and y-direction
     */
    public List<Cell> cornersCheck(Cell c, Grid g) {
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>(); 

	if(x == 0 && y == 0) {
	    neighbors.add(g.getCell(g.getXSize() - 1, g.getYSize() -1));
	}
	if(x == 0 && y == g.getYSize() - 1) {
	    neighbors.add(g.getCell(g.getXSize() - 1, 0));
	}
	if(x == g.getXSize() -1 && y == 0) {
	    neighbors.add(g.getCell(0, g.getYSize() -1));
	}
	if(x == g.getXSize() -1 && y == g.getYSize() - 1) {
	    neighbors.add(g.getCell(0, 0));
	}
	return neighbors;
    }   
}

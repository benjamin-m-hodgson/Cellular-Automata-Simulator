package simulation.neighborhoods.neighborhoodFactories;

import java.util.ArrayList;
import java.util.List;

import simulation.cell.Cell;
import simulation.grid.Grid;

public class TriangleNeighborhoodFactory extends NeighborhoodFactory {


    /**
     * Diagonal cells in upwards facing triangle
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Diagonal neighbors if in bounds 
     */
    public List<Cell> upTriangleFiniteDiagonal(Cell c, Grid g) {
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>();
	if(x > 1 && y > 0) {
	    neighbors.add(g.getCell(x - 2, y - 1));
	}
	if(x < g.getXSize() - 2 && y > 0) {
	    neighbors.add(g.getCell(x + 2, y - 1));
	}
	return neighbors;
    }

    /**
     * Diagonal cells in downward facing triangle
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Diagonal neighbors if in bounds 
     */
    public List<Cell> downTriangleFiniteDiagonal(Cell c, Grid g) {
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>();

	if(x < g.getXSize() - 2 && y > 0) {
	    neighbors.add(g.getCell(x + 2, y - 1));
	}
	if(x > 1 && y > 0) {
	    neighbors.add(g.getCell(x - 2, y - 1));
	}
	return neighbors;
    }

    /**
     * Returns wrapped neighbors that violate x-bounds for upwards/downwards facing triangles
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Diagonal neighbors if wrappable in x-direction
     */
    public List<Cell> xBoundsCheck(Cell c, Grid g) {
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>(); 

	if(x == 0 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(g.getXSize() -1, y + 1));
	}
	if(x == 0 && y > 0) {
	    neighbors.add(g.getCell(g.getXSize() -1, y - 1));
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
     * Returns wrapped neighbors that violate y-bounds for upwards/downwards facing triangles
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Diagonal neighbors if wrappable in x-direction
     */
    public List<Cell> yBoundsCheck(Cell c, Grid g) {
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
     * Returns wrapped neighbors that violate x-bounds and y-bounds for triangle shape
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

    /**
     * Returns wrapped neighbors that violate x-bounds specific to upwards facing triangle
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Diagonal neighbors if wrappable in x-direction
     */
    public List<Cell> upTriangleTorodialDiagonal(Cell c, Grid g) {
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>(); 

	if(x == 1 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(g.getXSize() - 2, y + 1));
	}
	if(x == g.getXSize() - 1 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(1, y + 1));
	}
	return neighbors;
    }

    /**
     * Returns wrapped neighbors that violate x-bounds specific to downward facing triangle
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Diagonal neighbors if wrappable in x-direction
     */
    public List<Cell> downTriangleTorodialDiagonal(Cell c, Grid g) {
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>(); 
	if(g.getXSize() < 2 || g.getYSize() < 2) return null;

	if(x == 1 && y > 0) {
	    neighbors.add(g.getCell(g.getXSize() - 2, y - 1));
	}
	if(x == g.getXSize() - 1 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(1, y - 1));
	}
	return neighbors;
    }


}

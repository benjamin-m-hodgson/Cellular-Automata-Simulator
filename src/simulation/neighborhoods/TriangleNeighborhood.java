package simulation.neighborhoods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simulation.grid.*;
import simulation.cell.*;

/**
 * Gets neighbors in certain positions/configurations
 * 
 * @author Katherine Van Dyk
 *
 */
public class TriangleNeighborhood extends Neighborhood {

    /**
     * Returns neighbors in t formation
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Neighbors north, south, east and west (if in bounds)
     */
    public Cell[] FiniteCardinal(Cell c, Grid g) {
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
	Cell[] retNeighbors = neighbors.toArray(new Cell[neighbors.size()]);
	return retNeighbors;
    }

    /**
     * Returns neighbors in x formation
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Neighbors diagonally (if in bounds)
     */
    public Cell[] FiniteDiagonal(Cell c, Grid g) {
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>();

	if(x < g.getXSize() - 1 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(x + 1, y + 1));
	}
	if(x > 0 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(x - 1, y + 1));
	}
	if(x > 0 && y > 0) {
	    neighbors.add(g.getCell(x - 1, y - 1));
	}
	if(x < g.getXSize() - 1 && y > 0) {
	    neighbors.add(g.getCell(x + 1, y - 1));
	}

	if ((c.getX() + c.getY()) % 2 == 0) {
	    neighbors.addAll(upTriangleFiniteDiagonal(c, g));
	}
	else {
	    neighbors.addAll(downTriangleFiniteDiagonal(c, g));
	}
	Cell[] retNeighbors = neighbors.toArray(new Cell[neighbors.size()]);
	return retNeighbors;
    }

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
     * Returns N, S, E and W neighbors of cells based on wrapping
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Neighbors north, south, east and west (if wrappable)
     */
    public Cell[] TorodialCardinal(Cell c, Grid g) {
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>();
	neighbors.addAll(Arrays.asList(FiniteCardinal(c ,g)));

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
	Cell[] retNeighbors = neighbors.toArray(new Cell[neighbors.size()]);
	return retNeighbors;
    }

    /**
     * Returns diagonal neighbors of cells based on wrapping
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Diagonal neighbors if wrappable
     */
    public Cell[] TorodialDiagonal(Cell c, Grid g) {

	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>(); 
	neighbors.addAll(Arrays.asList(FiniteDiagonal(c ,g)));

	// X-Coordinates on edge
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
	// Y-coordinates on edge
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
	// Handling both
	if(x == 0 && y == 0) {
	    neighbors.add(g.getCell(g.getXSize() - 1, g.getYSize() -1));
	}
	if(x == g.getXSize() -1 && y == g.getYSize() - 1) {
	    neighbors.add(g.getCell(0, 0));
	}
	if ((c.getX() + c.getY()) % 2 == 0) {
	    neighbors.addAll(upTriangleTorodialDiagonal(c, g));
	}
	else {
	    neighbors.addAll(downTriangleTorodialDiagonal(c, g));
	}
	Cell[] retNeighbors = neighbors.toArray(new Cell[neighbors.size()]);
	return retNeighbors;
    }

    
    /**
     * @return neighbors specific to upward facing triangles for @param c in grid @param g
     */
    public List<Cell> upTriangleTorodialDiagonal(Cell c, Grid g) {
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>(); 
	// X-Coordinates on edge
	if(x == 1 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(g.getXSize() - 2, y + 1));
	}
	if(x == g.getXSize() - 1 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(1, y + 1));
	}
	return neighbors;
    }

    /**
     * @return neighbors specific to downward facing triangles for @param c in grid @param g
     */
    public List<Cell> downTriangleTorodialDiagonal(Cell c, Grid g) {
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>(); 
	// Boundary check
	if(g.getXSize() < 2 || g.getYSize() < 2) return null;
	// X-Coordinates on edge
	if(x == 1 && y > 0) {
	    neighbors.add(g.getCell(g.getXSize() - 2, y - 1));
	}
	if(x == g.getXSize() - 1 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(1, y - 1));
	}
	return neighbors;
    }
}

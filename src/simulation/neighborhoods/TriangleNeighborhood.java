package simulation.neighborhoods;

import java.util.ArrayList;
import java.util.Arrays;
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
	if ((c.getX() + c.getY()) % 2 == 0) {
	    return upTriangleFiniteDiagonal(c, g);
	}
	else {
	    return downTriangleFiniteDiagonal(c, g);
	}
    }

    public Cell[] upTriangleFiniteDiagonal(Cell c, Grid g) {
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
	if(x > 1 && y > 0) {
	    neighbors.add(g.getCell(x - 2, y - 1));
	}
	if(x < g.getXSize() - 1 && y > 0) {
	    neighbors.add(g.getCell(x + 1, y - 1));
	}
	if(x < g.getXSize() - 2 && y > 0) {
	    neighbors.add(g.getCell(x + 2, y - 1));
	}
	Cell[] retNeighbors = neighbors.toArray(new Cell[neighbors.size()]);
	return retNeighbors;
    }

    public Cell[] downTriangleFiniteDiagonal(Cell c, Grid g) {
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>();

	if(x < g.getXSize() - 1 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(x + 1, y + 1));
	}
	if(x < g.getXSize() - 2 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(x + 2, y + 1));
	}
	if(x > 0 && y > 0) {
	    neighbors.add(g.getCell(x - 1, y - 1));
	}
	if(x > 0 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(x - 1, y + 1));
	}
	if(x > 1 && y < g.getYSize() - 1) {
	    neighbors.add(g.getCell(x - 2, y + 1));
	}
	if(x < g.getXSize() - 1 && y > 0) {
	    neighbors.add(g.getCell(x + 1, y - 1));
	}
	Cell[] retNeighbors = neighbors.toArray(new Cell[neighbors.size()]);
	return retNeighbors;
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
	// Handling x-coordinates on edge
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
	// Handling y-coordinates on edge
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
	//    neighbors.addAll(Arrays.asList(upTriangleTorodialDiagonal(c, g)));

	}
	else {
	//    neighbors.addAll(Arrays.asList(downTriangleTorodialDiagonal(c, g)));
	}
	Cell[] retNeighbors = neighbors.toArray(new Cell[neighbors.size()]);
	return retNeighbors;
    }

    // TO-DO 
    public Cell[] upTriangleTorodialDiagonal(Cell c, Grid g) {
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>(); 

	Cell[] retNeighbors = neighbors.toArray(new Cell[neighbors.size()]);
	return retNeighbors;
    }

    // TO-DO
    public Cell[] downTriangleTorodialDiagonal(Cell c, Grid g) {
	int x = c.getX();
	int y = c.getY();
	ArrayList<Cell> neighbors = new ArrayList<>(); 

	Cell[] retNeighbors = neighbors.toArray(new Cell[neighbors.size()]);
	return retNeighbors;
    }
}

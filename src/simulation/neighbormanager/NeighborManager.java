package simulation.neighbormanager;

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
public class NeighborManager {
	
	/**
	 * Returns neighbors in t formation
	 * 
	 * @param c: cell object being looked at
	 * @param g: current grid state
	 * @return Neighbors north, south, east and west (if in bounds)
	 */
	public Cell[] NSEWCells(Cell c, Grid g) {
		int x = c.getX();
		int y = c.getY();
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		neighbors.addAll(Arrays.asList(wrapNSEWCells(c ,g)));
		
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
	public Cell[] diagonalCells(Cell c, Grid g) {
		int x = c.getX();
		int y = c.getY();
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		neighbors.addAll(Arrays.asList(wrapDiagonalCells(c ,g)));
		
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
	private Cell[] wrapNSEWCells(Cell c, Grid g) {
		int x = c.getX();
		int y = c.getY();
		ArrayList<Cell> neighbors = new ArrayList<Cell>(); 

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
	private Cell[] wrapDiagonalCells(Cell c, Grid g) {
		int x = c.getX();
		int y = c.getY();
		ArrayList<Cell> neighbors = new ArrayList<Cell>(); 

		if(x == 0 && y < g.getYSize() - 1) {
			neighbors.add(g.getCell(g.getXSize() -1, y + 1));
		}
		if(x == g.getXSize() - 1 && y > 0) {
			neighbors.add(g.getCell(0, y - 1));
		}
		if(x > 0 && y == g.getYSize() - 1) {
			neighbors.add(g.getCell(x - 1, 0));
		}
		if(x < g.getXSize() - 1 && y == 0) {
			neighbors.add(g.getCell(x + 1, g.getYSize() -1));
		}
		Cell[] retNeighbors = neighbors.toArray(new Cell[neighbors.size()]);
		return retNeighbors;
	}
}

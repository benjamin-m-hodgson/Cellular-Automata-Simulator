package neighbormanager;

import java.util.ArrayList;
import java.util.Arrays;

import simulation.grid.*;
import simulation.cell.*;

public class NeighborManager {
	
	/**
	 * Returns all neighbors of cells in bounds
	 * 
	 * @param c
	 * @param g
	 * @return
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
	 * Returns all neighbors of cells in bounds
	 * 
	 * @param c
	 * @param g
	 * @return
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
	 * Returns all neighbors of cells based on wrapping
	 * 
	 * @param c
	 * @param g
	 * @return
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
	 * Returns all neighbors of cells based on wrapping
	 * 
	 * @param c
	 * @param g
	 * @return
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

package simulation.neighborhoods;

import java.util.ArrayList;
import java.util.Arrays;

import simulation.grid.*;
import simulation.neighborhoods.neighborhoodFactories.NeighborhoodFactory;
import simulation.cell.*;

/**
 * Gets neighbors in certain positions/configurations
 * 
 * @author Katherine Van Dyk
 *
 */
public class SquareNeighborhood extends Neighborhood {
	private NeighborhoodFactory FACTORY = new NeighborhoodFactory();

    /**
     * Returns neighbors in t formation
     * 
     * @param c: cell object being looked at
     * @param g: current grid state
     * @return Neighbors north, south, east and west (if in bounds)
     */
    public Cell[] FiniteCardinal(Cell c, Grid g) {
	ArrayList<Cell> neighbors = (ArrayList<Cell>) FACTORY.cardinalFiniteCheck(c, g);
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
	ArrayList<Cell> neighbors = (ArrayList<Cell>) FACTORY.diagonalFiniteCheck(c, g);
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
	ArrayList<Cell> neighbors = new ArrayList<>();
	neighbors.addAll(FACTORY.cardinalFiniteCheck(c, g));
	neighbors.addAll(FACTORY.cardinalWrapCheck(c,g));
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
	ArrayList<Cell> neighbors = new ArrayList<>(); 
	neighbors.addAll(Arrays.asList(FiniteDiagonal(c ,g)));
	neighbors.addAll(FACTORY.xBoundCheck(c ,g));
	neighbors.addAll(FACTORY.yBoundCheck(c ,g));
	neighbors.addAll(FACTORY.cornersCheck(c ,g));
	Cell[] retNeighbors = neighbors.toArray(new Cell[neighbors.size()]);
	return retNeighbors;
    }
   
}

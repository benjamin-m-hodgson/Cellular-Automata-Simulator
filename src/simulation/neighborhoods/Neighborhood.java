package simulation.neighborhoods;
import simulation.grid.*;
import simulation.cell.*;

/**
 * Gets neighbors in certain positions/configurations
 * 
 * @author Katherine Van Dyk
 *
 */
public abstract class Neighborhood {
	
	/**
	 * Returns neighbors in t formation
	 * 
	 * @param c: cell object being looked at
	 * @param g: current grid state
	 * @return Neighbors north, south, east and west (if in bounds)
	 */
	public abstract Cell[] NSEWCells(Cell c, Grid g);

	/**
	 * Returns neighbors in x formation
	 * 
	 * @param c: cell object being looked at
	 * @param g: current grid state
	 * @return Neighbors diagonally (if in bounds)
	 */
	public abstract Cell[] diagonalCells(Cell c, Grid g) ;
	
	/**
	 * Returns N, S, E and W neighbors of cells based on wrapping
	 * 
	 * @param c: cell object being looked at
	 * @param g: current grid state
	 * @return Neighbors north, south, east and west (if wrappable)
	 */
	protected abstract Cell[] wrapNSEWCells(Cell c, Grid g);
	
	/**
	 * Returns diagonal neighbors of cells based on wrapping
	 * 
	 * @param c: cell object being looked at
	 * @param g: current grid state
	 * @return Diagonal neighbors if wrappable
	 */
	protected abstract Cell[] wrapDiagonalCells(Cell c, Grid g); 
}

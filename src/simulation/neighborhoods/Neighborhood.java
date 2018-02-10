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
	 * Returns neighbors in cardinal directions, assuming wrapping
	 * 
	 * @param c: cell object being looked at
	 * @param g: current grid state
	 * @return Neighbors north, south, east and west (if in bounds)
	 */
	public abstract Cell[] TorodialCardinal(Cell c, Grid g);

	/**
	 * Returns neighbors in diagonal directions, assuming wrapping
	 * 
	 * @param c: cell object being looked at
	 * @param g: current grid state
	 * @return Neighbors north, south, east and west (if in bounds)
	 */
	public abstract Cell[] TorodialDiagonal(Cell c, Grid g);

	/**
	 * Returns neighbors in cardinal directions, assuming no wrapping
	 * 
	 * @param c: cell object being looked at
	 * @param g: current grid state
	 * @return Neighbors north, south, east and west (if in bounds)
	 */
	public abstract Cell[] FiniteCardinal(Cell c, Grid g);

	/**
	 * Returns neighbors in diagonal directions, assuming no wrapping
	 * 
	 * @param c: cell object being looked at
	 * @param g: current grid state
	 * @return Neighbors north, south, east and west (if in bounds)
	 */
	public abstract Cell[] FiniteDiagonal(Cell c, Grid g);
}




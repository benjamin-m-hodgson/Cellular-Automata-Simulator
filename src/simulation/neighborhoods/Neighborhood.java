package simulation.neighborhoods;
import simulation.grid.*;
import simulation.cell.*;

/**
 * Defines the neighborhood for a specific type cell, for both finite
 * and toroidal grid edges. A neighborhood is the collection of cells around a 
 * certain cell c that influence c's next state.
 * 
 * @author Katherine Van Dyk
 * @date 2/9/18
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




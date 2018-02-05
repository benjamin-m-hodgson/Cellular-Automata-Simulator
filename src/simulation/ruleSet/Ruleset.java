package simulation.ruleSet;
import simulation.grid.*;

public interface Ruleset {
	
	/**
	 * Returns new states for all cells in a grid
	 */
	void processCells();
	
	/**
	 * Sets the current grid for a particular ruleset
	 * 
	 * @param g
	 */
	void setGrid(Grid g);
	
}

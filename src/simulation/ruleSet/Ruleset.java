package simulation.ruleSet;
import simulation.cell.*;
import simulation.grid.*;

public interface Ruleset {

	void processCells(Grid g);
	//int processCell(Cell c, Cell[] neighbors);
	int neighborCount(Cell[] neighbors);
	
	/**
	 * Important change to plan: neighbors vary 
	 * @param c
	 * @return
	 */
	Cell[] getNeighbors(Cell c, Grid g);

}

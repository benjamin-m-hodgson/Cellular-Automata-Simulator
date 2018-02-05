package simulation.ruleSet;
import simulation.cell.*;
import simulation.grid.*;

public interface Ruleset {

	void processCells();
	void setGrid(Grid g);
	Cell[] getNeighbors(Cell c);

}

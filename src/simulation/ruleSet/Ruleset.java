package simulation.ruleSet;
import simulation.cell.*;

public interface Ruleset {
	
	int processCell(Cell c, Cell[] neighbors);
	
	int neighborCount(Cell[] neighbors);

}

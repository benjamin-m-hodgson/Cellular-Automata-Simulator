package simulation.ruleSet;

import simulation.cell.AntCell;
import simulation.cell.Cell;

public class AntRuleset extends Ruleset {
	
	private int BARE = 0;
	private int FOOD = 1;
	private int NEST = 2;
	private int OBSTACLE = 4;

	@Override
	public int processCell(Cell cell) {
		AntCell c = (AntCell) cell;
		if (c.hasFood()) {
			returnToNest(c);
		} else findFood(c);
		return 0;
	}
	
	private void returnToNest(AntCell c) {
		if (c.getState() == FOOD) {
			
		}
	}
	
	private void findFood(AntCell c) {
		
	}

}

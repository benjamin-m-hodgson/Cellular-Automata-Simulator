package simulation;

import java.util.HashMap;

import simulation.cell.*;
import simulation.grid.*;
import simulation.ruleSet.Ruleset;

/**
 * Updates the current grid/ruleset parameters, based on what the engine passes to it
 * 
 */
public class CurrentSimulation{

	
	// holds only the shape array, is passed the grid/ruleset or naw
	public CurrentSimulation() {
		
	}
	
	
	public void update(Grid grid, Ruleset r) {
		processCells(grid, r);
		updateDisplay();
	}
	
	private void updateDisplay(){
		
	}
	
	/**
	 * **How do we get grid to it
	 * Returns new cell states for entire grid
	 */
	public void processCells(Grid g, Ruleset r) {
		for(Cell[] row  : g.getCells()) {
			for(Cell cell: row) {
				if(cell.getMove()) continue;
				else{
					int newState = r.processCell(cell);
					cell.setState(newState);
				}
			}
		}
		updateStates();
	}

	/**
	 * Updates states for all cells at once
	 */
	public void updateStates(Grid g) {
		for(Cell[] row : g.getCells()) {
			for(Cell cell : row) {
				cell.updateState();
				cell.setMove(false);
			}
		}
	}

}

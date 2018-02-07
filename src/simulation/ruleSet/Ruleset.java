package simulation.ruleSet;
import simulation.cell.Cell;
import simulation.grid.*;

public abstract class Ruleset {

	protected Grid GRID;

	/**
	 * Returns new states for all cells in a grid
	 */
	//void processCells();

	/**
	 * Sets the current grid for a particular ruleset
	 * 
	 * @param g
	 */
	//void setGrid(Grid g);

	/**
	 * Sets current simulation's grid
	 */

	public void setGrid(Grid g) {
		GRID = g;	
	}	

	/**
	 * Returns new cell states for entire grid
	 */

	public void processCells() {
		for(Cell[] row  : GRID.getCells()) {
			for(Cell cell: row) {
				if(cell.getMove()) continue;
				else{
					int newState = processCell(cell);
					cell.setState(newState);
				}
			}
		}
		updateStates();
	}


	public int processCell(Cell cell) {
		return 0;
	}

	/**
	 * Updates states for all cells at once
	 */
	public void updateStates() {
		for(int r = 0; r < GRID.getXSize(); r++) {
			for(int c = 0; c < GRID.getYSize(); c++) {
				Cell cell = GRID.getCell(r, c);
				cell.updateState();
				cell.setMove(false);
			}
		}
	}

}

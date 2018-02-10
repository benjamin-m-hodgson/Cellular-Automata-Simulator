package simulation.ruleSet;
import simulation.cell.Cell;
import simulation.grid.*;

/**
 * Superclasss for handling processing of cells based on simulation rules
 * 
 * @author katherinevandyk
 *
 */
public abstract class Ruleset {
	protected Grid GRID;

	/**
	 * Sets current simulation's grid
	 */
	public void setGrid(Grid g) {
		GRID = g;	
	}	

	/**
	 * Abstract method for processing cell's next state
	 * 
	 * @param cell
	 * @return int representing state of next cell
	 */
	public abstract int processCell(Cell cell);

	/**
	 * Returns new cell states for entire grid
	 */
	public void processCells() {
		for(Cell[] row  : GRID.getCells()) {
			for(Cell cell: row) {
				if(cell.getMove()) { 
					continue;
				}
				else {
					int newState = processCell(cell);
					cell.setState(newState);
				}
			}
		}
		updateStates();
	}

	/**
	 * Updates states for all cells at once
	 */
	public void updateStates() {
		for(Cell[] row : GRID.getCells()) {
			for(Cell cell : row) {
				cell.updateState();
				cell.setMove(false);
			}
		}
	}
}

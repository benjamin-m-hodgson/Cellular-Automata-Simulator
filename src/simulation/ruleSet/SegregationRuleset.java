package simulation.ruleSet;
import neighbormanager.SegregationNeighborManager;
import simulation.cell.*;
import simulation.grid.Grid;

/**
 * RULES: 
 * Satisfied Cell: % neighbors satisfied < tolerance -> Dissatisfied
 * Dissatisfied Cell: moves to random vacant spot
 * 
 *  @param Cell c: cell whose state is being evaluated
 *  @param Cell[] neighbors: neighbors of c
 *  @author Katherine Van Dyk
 *  @author Ben Hodgson
 */
public class SegregationRuleset implements Ruleset {

	private int VACANT = 2;
	private double TOLERANCE;
	private Grid GRID;
	private SegregationNeighborManager NEIGHBOR_MANAGER = new SegregationNeighborManager();

	/**
	 * Constructor that sets simulation parameters
	 * 
	 * @param tolerance
	 */
	public SegregationRuleset(double tolerance) {
		this.TOLERANCE = tolerance;
	}
	

	/**
	 * Sets grid to current grid 
	 * 
	 * @param g: Current simulation grid
	 */
	@Override
	public void setGrid(Grid g) {
		GRID = g;
	}

	/**
	 * Updates all states in current grid
	 */
	@Override
	public void processCells() {
		for(Cell row[] : GRID.getCells()) {
			for(Cell cell : row) {
				SegregationCell sCell = (SegregationCell) cell;
				if(NEIGHBOR_MANAGER.getNeighborSatisfaction(sCell, GRID) < TOLERANCE) {
					moveCell(sCell);
				}
				else {
					sCell.setState(sCell.getState());
				}
			}
		}
		
		cleanMove();
		updateStates();
	}	
	
	/**
	 * Moves cell to vacant spot
	 * 
	 * @param cell
	 */
	private void moveCell(SegregationCell cell) {
		SegregationCell newCell = NEIGHBOR_MANAGER.findVacantCell(GRID);
		if(newCell == null) return;
		else{
			newCell.setState(cell.getState());
			newCell.setMove(true);
			cell.setState(VACANT);
			cell.setMove(true);
		}
	}

	/**
	 * Changes all setMoves() to false (so that cells don't overwrite each other)
	 */
	private void cleanMove() {
		for(int r = 0; r < GRID.getXSize(); r++) {
			for(int c = 0; c < GRID.getYSize(); c++) {
				SegregationCell cell = (SegregationCell) GRID.getCell(r,c);
				cell.setMove(false);
			}
		}
	}
	
	public void updateStates() {
		for(int r = 0; r < GRID.getXSize(); r++) {
			for(int c = 0; c < GRID.getYSize(); c++) {
				SegregationCell cell = (SegregationCell) GRID.getCell(r, c);
				cell.updateState();
			}
		}
	}

}


package simulation.ruleSet;
import simulation.cell.*;
import simulation.neighbormanager.SegregationNeighborManager;

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
public class SegregationRuleset extends Ruleset {

	private int VACANT = 2;
	private double TOLERANCE;
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
	 * Updates all states in current grid
	 */
	@Override
	public int processCell(Cell cell) {
		SegregationCell sCell = (SegregationCell) cell;
		if(NEIGHBOR_MANAGER.getNeighborSatisfaction(sCell, GRID) < TOLERANCE && sCell.getState() != VACANT) {
			return moveCell(sCell);
		}
		else {
			return  sCell.getState();
		}
	}
	
	/**
	 * Moves cell to vacant spot
	 * 
	 * @param cell
	 */
	private int moveCell(SegregationCell cell) {
		SegregationCell newCell = NEIGHBOR_MANAGER.findVacantCell(GRID);
		if(newCell == null) {
			return cell.getState();
		}
		else{
			newCell.setState(cell.getState());
			newCell.setMove(true);
			return VACANT;
		}
	}
}


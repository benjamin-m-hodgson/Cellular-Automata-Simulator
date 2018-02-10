package simulation.ruleSet;
import simulation.cell.*;
import simulation.ruleSet.neighborManager.*;

/**
 * Segregation simulation ruleset
 * 
 *  @param Cell c: cell whose state is being evaluated
 *  @param Cell[] neighbors: neighbors of c
 *  @author Katherine Van Dyk
 *  @author Ben Hodgson
 */
public class SegregationRuleset extends Ruleset {

	private int VACANT = 2;
	private double TOLERANCE;
	private SegregationNeighborManager NEIGHBOR_MANAGER;

	/**
	 * Constructor that sets simulation parameters
	 * 
	 * @param tolerance: tolerance of neighbors of opposite group
	 */
	public SegregationRuleset(double tolerance) {
		this.TOLERANCE = tolerance;
		this.NEIGHBOR_MANAGER = new SegregationNeighborManager("Square");
	}

	/**
	 * Updates all states in current grid
	 * 
	 * @param cell : Cell to be processed
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
	
	public double getTolerance() {
		return TOLERANCE;
	}
}


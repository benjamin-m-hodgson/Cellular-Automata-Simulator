package simulation.ruleSet;
import simulation.cell.*;
import simulation.neighborhoods.Neighborhood;
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
    }

    /**
     * Sets neighbor manager based on edge type @param finite and neighborhood based on shape type @param n
     */
    @Override
    public void setNeighborManager(Neighborhood n, boolean finite) {
	this.NEIGHBOR_MANAGER = new SegregationNeighborManager(n, finite);
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

    /**
     * @return double representing tolerance parameter for segregation ruleset
     */
    public double getTolerance() {
	return TOLERANCE;
    }
    
    /**
     * Sets tolerance parameter to @param n
     */
    public void setTolerance(double n) {
	TOLERANCE = n;
    }

}


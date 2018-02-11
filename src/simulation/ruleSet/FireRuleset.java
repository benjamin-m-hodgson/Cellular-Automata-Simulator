package simulation.ruleSet;
import java.util.Random;
import simulation.cell.*;
import simulation.neighborhoods.Neighborhood;
import simulation.neighborhoods.SquareNeighborhood;
import simulation.ruleSet.neighborManager.*;

/**
 * Calculates updated cell states based on Fire Ruleset simulation.
 * 
 * RULES: 
 * Tree Cell: If tree surrounding it burns, and rand # < probCatch -> burn
 * Burning Cell: Always burning
 * 
 *  @param Cell c: cell whose state is being evaluated
 *  @param Cell[] neighbors: neighbors of c
 *  @author Katherine Van Dyk
 *  @author Ben Hodgson
 */
public class FireRuleset extends Ruleset {

    private int VACANT = 0;
    private int TREE = 1;
    private int BURNING = 2;
    private FireNeighborManager NEIGHBOR_MANAGER;
    double PROBCATCH;

    /**
     * Sets neighbor manager based on edge type @param finite and neighborhood based on shape type @param n
     */
    @Override
    public void setNeighborManager(Neighborhood n, boolean finite) {
	this.NEIGHBOR_MANAGER = new FireNeighborManager(n, finite);
    }

    /**
     * Constructor that sets @param probCatch for current Fire simulation
     */
    public FireRuleset(double probCatch) {
	this.PROBCATCH = probCatch;
	this.NEIGHBOR_MANAGER = new FireNeighborManager(new SquareNeighborhood(), false); // default neighbor manager
    }

    /**
     * @return double representing the probability that a tree catches on fire (PROBCATCH parameter)
     */
    public double getProbCatch() {
	return PROBCATCH;
    }

    /**
     * Sets PROBCATCH parameter to @param d
     */
    public void setProbCatch(Double d) {
	PROBCATCH = d;
	System.out.println(PROBCATCH);
    }

    /**
     * Processes single cell in grid
     * 
     * @param c: cell to be processed
     * @return int: new state
     */
    @Override
    public int processCell(Cell c) {
	Random rand = new Random();
	if(NEIGHBOR_MANAGER.neighborCount(c, GRID, BURNING) > 0 
		&& rand.nextDouble() < this.PROBCATCH
		&& c.getState() != VACANT) {
	    return BURNING;
	}
	else if (c.getState() == TREE) {
	    return TREE;
	} 
	else {
	    return VACANT;
	}
    }
}

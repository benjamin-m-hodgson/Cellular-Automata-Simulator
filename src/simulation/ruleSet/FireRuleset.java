package simulation.ruleSet;
import java.util.Random;
import simulation.cell.*;
import simulation.neighbormanager.FireNeighborManager;

/**
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
	 * Sets initial parameters for current simulation
	 * 
	 * @param probCatch
	 */
	public FireRuleset(double probCatch) {
		this.PROBCATCH = probCatch;
		this.NEIGHBOR_MANAGER = new FireNeighborManager();
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
		if(NEIGHBOR_MANAGER.neighborCount(c, GRID) > 0 
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

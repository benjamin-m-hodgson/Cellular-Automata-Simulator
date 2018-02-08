package simulation.ruleSet;
import simulation.cell.*;
import simulation.neighbormanager.GameOfLifeNeighborManager;

/**
 * Game of Life simulation ruleset
 * 
 *  @param Cell c: cell whose state is being evaluated
 *  @param Cell[] neighbors: neighbors of c
 *  @author Katherine Van Dyk
 *  @author Ben Hodgson
 */
public class GameOfLifeRuleset extends Ruleset {
	private int MAXLIFE;
	private int MINLIFE;
	private int BIRTH;
	private GameOfLifeNeighborManager NEIGHBOR_MANAGER; 

	private static final int LIVE = 0;
	private static final int DEAD = 1;

	/**
	 * Constructor that sets simulation parameters
	 * 
	 * @param minLife: min number of live neighbors needed to survive
	 * @param maxLife: max number of live neighbors needed to survive
	 * @param birth: exact number of live neighbors needed to give birth
	 */
	public GameOfLifeRuleset(int minLife, int maxLife, int birth) {
		this.NEIGHBOR_MANAGER = new GameOfLifeNeighborManager();
		this.MINLIFE = minLife;
		this.MAXLIFE = maxLife;
		this.BIRTH = birth;
	}
	
	
	/**
	 * Returns next state of input cell
	 * 
	 * @param c: Cell whose state is being updated
	 * @param neighbors: Neighbors of cell c
	 * @return
	 */
	@Override
	public int processCell(Cell c) {
		int liveCount = NEIGHBOR_MANAGER.neighborCount(c, GRID);
		if(c.getState() == LIVE) {
			if(liveCount > this.MINLIFE || liveCount < this.MAXLIFE) {
				return LIVE;
			}
			else {
				return DEAD;
			}
		}
		else {
			if(liveCount == BIRTH) {
				return LIVE;
			}
			else {
				return DEAD;
			}
		}
	}

}
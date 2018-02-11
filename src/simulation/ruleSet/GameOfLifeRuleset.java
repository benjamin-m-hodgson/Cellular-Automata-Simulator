package simulation.ruleSet;
import simulation.cell.*;
import simulation.neighborhoods.Neighborhood;
import simulation.neighborhoods.SquareNeighborhood;
import simulation.ruleSet.neighborManager.*;

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
	this.NEIGHBOR_MANAGER = new GameOfLifeNeighborManager(new SquareNeighborhood(), false);
	this.MINLIFE = minLife;
	this.MAXLIFE = maxLife;
	this.BIRTH = birth;
    }

    /**
     * Sets neighbor manager based on edge type @param finite and neighborhood based on shape type @param n
     */
    @Override
    public void setNeighborManager(Neighborhood n, boolean finite) {
	this.NEIGHBOR_MANAGER = new GameOfLifeNeighborManager(n, finite);

    }

    /**
     * Returns next state of input cell @param c
     * 
     * @return int: next state of cell
     */
    @Override
    public int processCell(Cell c) {
	int liveCount = NEIGHBOR_MANAGER.neighborCount(c, GRID, LIVE);
	if(c.getState() == LIVE) {
	    if(liveCount > this.MINLIFE || liveCount < this.MAXLIFE) return LIVE;
	    else return DEAD;
	}
	else {
	    if(liveCount == BIRTH) return LIVE;
	    else return DEAD;
	}
    }

    /**
     * @return int representing minimum amt of live cells needed for cell to live
     */
    public int getMinLife() {
	return MINLIFE;
    }
    
    /**
     * @return int representing maximum amt of live cells for cell not to die
     */
    public int getMaxLife() {
	return MAXLIFE;
    }

    /**
     * @return int representing exact number of live cells needed to give birth
     */
    public int getBirth() {
	return BIRTH;
    }

    /**
     * Sets minimum number of lives to @param n
     */
    public void setMinLife(int n) {
	MINLIFE = n;
    }

    /**
     * Sets number of cells needed to give birth to @param n
     */
    public void setBirth(int n) {
	BIRTH = n;
    }

    /**
     * Sets maximum number of lives to @param n
     */
    public void setMaxLife(int n) {
	MAXLIFE = n;
    }
}
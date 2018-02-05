package simulation.ruleSet;
import neighbormanager.GameOfLifeNeighborManager;
import simulation.cell.*;
import simulation.grid.Grid;

/**
 * RULES: 
 * Live Cell: < MINLIFE or > MAXLIFE neighbors -> cell dies
 * Dead Cell: Exactly BIRTH neighbors -> cell comes back to life
 * 
 *  @param Cell c: cell whose state is being evaluated
 *  @param Cell[] neighbors: neighbors of c
 *  @author Katherine Van Dyk
 *  @author Ben Hodgson
 */
public class GameOfLifeRuleset implements Ruleset {
	private int MAXLIFE;
	private int MINLIFE;
	private int BIRTH;
	private Grid GRID;
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
	 * Sets current simulation's grid
	 */
	@Override
	public void setGrid(Grid g) {
		GRID = g;	
	}	
	
	/**
	 * Returns new cell states for entire grid
	 */
	@Override
	public void processCells() {
		for(Cell[] row  : GRID.getCells()) {
			for(Cell cell: row) {
				int newState = processCell((GameOfLifeCell) cell);
				cell.setState(newState);
			}
		}
	}
	
	/**
	 * Returns next state of input cell
	 * 
	 * @param c: Cell whose state is being updated
	 * @param neighbors: Neighbors of cell c
	 * @return
	 */
	public int processCell(GameOfLifeCell c) {
		int liveCount = NEIGHBOR_MANAGER.neighborCount(c, GRID);
		if(c.getState() == LIVE) {
			if(liveCount > this.MINLIFE || liveCount < this.MAXLIFE) return LIVE;
			else return DEAD;
		}
		else {
			if(liveCount == BIRTH) return LIVE;
			else return DEAD;
		}
	}

}
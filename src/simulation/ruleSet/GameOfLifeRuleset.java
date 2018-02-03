package simulation.ruleSet;
import simulation.cell.*;
import simulation.grid.Grid;

public class GameOfLifeRuleset implements Ruleset {
	private int MAXLIFE;
	private int MINLIFE;
	private int BIRTH;

	private static final int LIVE = 1;
	private static final int DEAD = 0;
	
	public GameOfLifeRuleset(int minLife, int maxLife, int birth) {
		this.MINLIFE = minLife;
		this.MAXLIFE = maxLife;
		this.BIRTH = birth;
	}

	/**
	 * RULES: 
	 * Live Cell: < MINLIFE or > MAXLIFE neighbors -> cell dies
	 * Dead Cell: Exactly BIRTH neighbors -> cell comes back to life
	 * 
	 *  @param Cell c: cell whose state is being evaluated
	 *  @param Cell[] neighbors: neighbors of c
	 */
	@Override
	public int processCell(Cell c, Cell[] neighbors) {
		int liveCount = neighborCount(neighbors);
		if(c.getState() == LIVE) {
			if(liveCount >= this.MINLIFE || liveCount <= this.MAXLIFE) return LIVE;
			else return DEAD;
		}
		else {
			if(liveCount == BIRTH) return LIVE;
			else return DEAD;
		}
	}

	@Override
	public int neighborCount(Cell[] neighbors) {
		int count = 0;
		for(Cell neighbor : neighbors) {
			if(neighbor.getState() == LIVE) {
				count++;
			}
		}
		return count;
	}

	@Override
	public Cell[] getNeighbors(Cell c, Grid g) {
		// TODO Auto-generated method stub
		return null;
	}

}
package simulation.ruleSet;

import simulation.cell.Cell;
import simulation.cell.SugarCell;
import simulation.neighborhoods.Neighborhood;
import simulation.neighborhoods.SquareNeighborhood;
import simulation.ruleSet.neighborManager.SugarNeighborManager;

/**
 * 
 * Rules: A cell is either a patch or an agent. 
 * Each turn, a cell generates sugar up to a maximum, while an agent loses sugar based on its metabolism
 * An agent searches its vision in the four cardinal directions for the cell with the most sugar. It then goes there and takes the sugar.
 * If an agent's sugar reaches zero, it dies.
 *
 */
public class SugarRuleset extends Ruleset{
	
	private int PATCH = 0;
	private int AGENT = 1;
	private int myRegenRate;
	private int myRegenInterval;
	private SugarNeighborManager NEIGHBOR_MANAGER;
	
	/**
	 * Determines neighbor manager to use
	 */
	@Override
	public void setNeighborManager(Neighborhood n, boolean finite) {
	    this.NEIGHBOR_MANAGER = new SugarNeighborManager(n, finite); 
	}
	
	/**
	 * Sets initial parameters
	 * @param regenRate
	 * @param regenInterval
	 */
	public SugarRuleset(int regenRate, int regenInterval) {
		myRegenRate = regenRate;
		myRegenInterval = regenInterval;
		NEIGHBOR_MANAGER = new SugarNeighborManager(new SquareNeighborhood(), false);
	}
	
	/**
	 * Processes cells for each turn, determines whether a cell should behave as a patch or an agent
	 */
	@Override
	public int processCell(Cell cell) {
		SugarCell c = (SugarCell) cell;
		if (c.getState() == PATCH) {
			processPatch(c);
			return PATCH;
		} else if (c.getState() == AGENT) {
			processAgent(c);
			return AGENT;
		}
		return 0;
	}
	
	/**
	 * Handles sugar regeneration for each patch
	 * @param c
	 */
	private void processPatch(SugarCell c) {
		if(c.getTicks() >= myRegenInterval) {
			c.resetTicks();
			if(c.getSugar() < c.getMaxSugar() - myRegenRate) {
				c.setSugar(c.getSugar() + myRegenRate);
			} else if (c.getSugar() == c.getMaxSugar() - myRegenRate) c.setSugar(c.getMaxSugar());
		}
	}
	
	/**
	 * Handles agent's neighbor selection and metabolism
	 * @param c
	 */
	private void processAgent(SugarCell c) {
		Cell[] neighbors = NEIGHBOR_MANAGER.getNeighbors(c, GRID, c.getVision());
		int max = 0;
		int maxIndex = 0;
		for (int k = 0; k < neighbors.length; k++) {
			SugarCell sc = (SugarCell) neighbors[k];
			if (sc.getSugar() > max) {
				max = sc.getSugar();
				maxIndex = k;
			}
		}
		SugarCell target = (SugarCell) neighbors[maxIndex];
		agentEat(c, target);
		SugarCell newAgent = target;
		newAgent.setAgentSugar(newAgent.getAgentSugar() - newAgent.getMetabolism());
		if (newAgent.getMetabolism() <= 0) {
			newAgent.setState(PATCH);
			newAgent.resetTicks();
		}
	}
	
	/**
	 * Handles instances of an agent eating the sugar on a patch, switches cell states and moves parameters of agent to new cell
	 * @param c
	 * @param target
	 */
	private void agentEat(SugarCell c, SugarCell target) {
		target.setState(AGENT);
		target.setAgentSugar(target.getSugar() + c.getAgentSugar());
		target.setSugar(0);
		target.setMetabolism(c.getMetabolism());
		target.setVision(c.getVision());
		c.setState(PATCH);
		c.setSugar(0);
		c.resetTicks();
	}
}

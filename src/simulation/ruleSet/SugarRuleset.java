package simulation.ruleSet;

import simulation.cell.Cell;
import simulation.cell.SugarCell;
import simulation.neighborhoods.Neighborhood;
import simulation.neighborhoods.SquareNeighborhood;
import simulation.ruleSet.neighborManager.SugarNeighborManager;

public class SugarRuleset extends Ruleset{
	
	private int PATCH = 0;
	private int AGENT = 1;
	private int myRegenRate;
	private int myRegenInterval;
	private SugarNeighborManager NEIGHBOR_MANAGER;
	
	@Override
	public void setNeighborManager(Neighborhood n, boolean finite) {
	    this.NEIGHBOR_MANAGER = new SugarNeighborManager(n, finite); 
	}
	
	public SugarRuleset(int regenRate, int regenInterval) {
		myRegenRate = regenRate;
		myRegenInterval = regenInterval;
		NEIGHBOR_MANAGER = new SugarNeighborManager(new SquareNeighborhood(), false);
	}
	
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
	
	private void processPatch(SugarCell c) {
		if(c.getTicks() >= myRegenInterval) {
			c.resetTicks();
			if(c.getSugar() < c.getMaxSugar() - myRegenRate) {
				c.setSugar(c.getSugar() + myRegenRate);
			} else if (c.getSugar() == c.getMaxSugar() - myRegenRate) c.setSugar(c.getMaxSugar());
		}
	}
	
	private void processAgent(SugarCell c) {
		Cell[] neighbors = NEIGHBOR_MANAGER.getNeighbors(c,GRID,c.getVision());
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

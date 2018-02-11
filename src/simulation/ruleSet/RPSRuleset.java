package simulation.ruleSet;

import java.util.Random;

import simulation.cell.Cell;
import simulation.cell.RPSCell;
import simulation.ruleSet.neighborManager.RPSNeighborManager;

public class RPSRuleset extends Ruleset {
	
	private int WHITE = 0;
	private int ROCK = 1;
	private int PAPER = 2;
	private int SCISSORS = 3;
	private RPSNeighborManager NEIGHBOR_MANAGER;
	
	public RPSRuleset() {
		NEIGHBOR_MANAGER = new RPSNeighborManager();
	}
	
	@Override
	public int processCell(Cell c) {
		Random rand = new Random();
		RPSCell cell = (RPSCell) c;
		int neighbor = rand.nextInt(8);
		RPSCell neighborCell = (RPSCell) NEIGHBOR_MANAGER.getNeighbors(c, GRID)[neighbor];
		if (cell.getState() == WHITE) {
			if(neighborCell.getState() != WHITE && neighborCell.getGradient() < 9) {
				cell.setState(neighborCell.getState());
				cell.setGradient(neighborCell.getGradient() + 1);
			}
		} else if (cell.getState() == ROCK) {
			if(neighborCell.getState() == SCISSORS) {
				eat(cell, neighborCell);
			} else if (neighborCell.getState() == PAPER) {
				eaten(cell, neighborCell);
			}
		} else if (cell.getState() == PAPER) {
			if (neighborCell.getState() == ROCK) {
				eat(cell, neighborCell);
			} else if (neighborCell.getState() == SCISSORS) {
				eaten(cell, neighborCell);
			}
		} else if (cell.getState() == SCISSORS) {
			if (neighborCell.getState() == PAPER) {
				eat(cell, neighborCell);
			} else if (neighborCell.getState() == ROCK) {
				eaten(cell, neighborCell);
			}
		}
		return c.getState();
	}
	
	public void eat(RPSCell c, RPSCell n) {
		if(c.getGradient() > 0) c.upgrade();
		if(n.getGradient() < 9) {
			n.downgrade();
		} else n.setState(c.getState());
	}
	
	public void eaten(RPSCell c, RPSCell n) {
		if(c.getGradient() < 9) {
			c.downgrade();
		} else c.setState(n.getState());
		if(n.getGradient() > 0) n.upgrade();
	}

}

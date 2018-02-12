package simulation.ruleSet;

import java.util.Random;
import simulation.cell.Cell;
import simulation.cell.RPSCell;
import simulation.neighborhoods.Neighborhood;
import simulation.neighborhoods.SquareNeighborhood;
import simulation.ruleSet.neighborManager.RPSNeighborManager;

/**
 * 
 * Ruleset for Rock, Paper, Scissors simulation
 * Rules: a cell is either red, green, blue, or empty
 * Each cell has a power level
 * A cell chooses a neighbor at random and fights. If it wins, it is upgraded and its victim is downgraded. If it loses, the opposite occurs.
 * Red beats green. green beats blue, and blue beats red.
 *
 */
public class RPSRuleset extends Ruleset {

    private int WHITE = 0;
    private int ROCK = 1;
    private int PAPER = 2;
    private int SCISSORS = 3;
    private RPSNeighborManager NEIGHBOR_MANAGER;

    /**
     * Sets initial parameters for current simulation
     * 
     */
    public RPSRuleset() {
	this.NEIGHBOR_MANAGER = new RPSNeighborManager(new SquareNeighborhood(), false); // default neighbor manager
    }
    
    /**
     * Determines neighbor manager to be used
     */
    @Override
    public void setNeighborManager(Neighborhood n, boolean finite) {
	this.NEIGHBOR_MANAGER = new RPSNeighborManager(n, finite); 
    }

    /**
     * Processes cells each turn; chooses a random neighbor, and then determines who wins and take sth eappropriate measures.
     */
    @Override
    public int processCell(Cell c) {
	Random rand = new Random();
	RPSCell cell = (RPSCell) c;
	Cell[] neighbors = NEIGHBOR_MANAGER.getNeighbors(c, GRID);
	RPSCell neighborCell = ((RPSCell) neighbors[rand.nextInt(neighbors.length)]);
	if (cell.getState() == WHITE) {
	    handleWhite(cell, neighborCell);
	} else if (cell.getState() == ROCK) {
	    handleRock(cell, neighborCell);
	} else if (cell.getState() == PAPER) {
	    handlePaper(cell, neighborCell);
	} else if (cell.getState() == SCISSORS) {
	    handleScissors(cell, neighborCell);
	}
	return c.getState();
    }

    /**
     * Handles WHITE @param cell's updated state based on random @param neighborCell state
     */
    private void handleWhite(RPSCell cell, RPSCell neighborCell) {
	if(neighborCell.getState() != WHITE && neighborCell.getGradient() < 9) {
	    cell.setState(neighborCell.getState());
	    cell.setGradient(neighborCell.getGradient() + 1);
	}
    }

    /**
     * Handles ROCK @param cell's updated state based on random @param neighborCell state
     */
    private void handleRock(RPSCell cell, RPSCell neighborCell) {
	if(neighborCell.getState() == SCISSORS) {
	    eat(cell, neighborCell);
	} else if (neighborCell.getState() == PAPER) {
	    eaten(cell, neighborCell);
	}
    }

    /**
     * Handles PAPER @param cell's updated state based on random @param neighborCell state
     */
    private void handlePaper(RPSCell cell, RPSCell neighborCell) {
	if (neighborCell.getState() == ROCK) {
	    eat(cell, neighborCell);
	} else if (neighborCell.getState() == SCISSORS) {
	    eaten(cell, neighborCell);
	}
    }

    /**
     * Handles SCISSORS @param cell's updated state based on random @param neighborCell state
     */
    private void handleScissors(RPSCell cell, RPSCell neighborCell) {
	if (neighborCell.getState() == PAPER) {
	    eat(cell, neighborCell);
	} else if (neighborCell.getState() == ROCK) {
	    eaten(cell, neighborCell);
	}
    }

    /**
     * Helper method to handle when a cell defeats its neighbor
     * @param c
     * @param n
     */
    public void eat(RPSCell c, RPSCell n) {
	if(c.getGradient() > 0) c.upgrade();
	if(n.getGradient() < 9) {
	    n.downgrade();
	} else n.setState(c.getState());
    }

    /**
     * Helper method to handle when a cell is defeated by its neighbor
     * @param c
     * @param n
     */
    public void eaten(RPSCell c, RPSCell n) {
	if(c.getGradient() < 9) {
	    c.downgrade();
	} else c.setState(n.getState());
	if(n.getGradient() > 0) n.upgrade();
    }
}

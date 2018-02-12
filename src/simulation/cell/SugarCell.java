package simulation.cell;

import javafx.scene.paint.Color;

/**
 * 
 * Cell object for SugarScape simulation
 *
 */

public class SugarCell extends Cell {

    private int PATCH = 0;
    private int AGENT = 1;
    private int mySugar;
    private int myMaxSugar;
    private int myAgentSugar;
    private int myAgentMetabolism;
    private int myAgentVision;
    private int myTicks;
    
    /**
     * Constructor for Sugar Cell
     * @param x
     * @param y
     * @param state
     * @param sugar
     * @param maxSugar
     * @param agentSugar
     * @param agentMetabolism
     * @param agentVision
     */

    public SugarCell(int x, int y, int state, int sugar, int maxSugar, int agentSugar, int agentMetabolism, int agentVision) {
	super(x, y, state);
	mySugar = sugar;
	myMaxSugar = maxSugar;
	myAgentSugar = agentSugar;
	myAgentMetabolism = agentMetabolism;
	myAgentVision = agentVision;
    }

    /**
     * Determines color of Sugar Cell based on state and amount of sugar
     * @return new color of cell
     */
    
    @Override
    public Color colorCell() {
	if (myState == AGENT) {
	    return Color.RED;
	} else if (myState == PATCH) {
	    double gradient = (double) mySugar / (double) myMaxSugar;
	    return Color.web("#FFA500", gradient);
	} else return Color.BLACK;

    }
    
    /**
     * Gets number of ticks until new sugar generates
     * @return number of ticks
     */

    public int getTicks() {
	return myTicks;
    }

    /**
     * Resets ticks until sugar generation to zero
     */
    public void resetTicks() {
	myTicks = 0;
    }

    /**
     * Gets amount of sugar on a patch
     * @return integer value of sugar amount
     */
    public int getSugar() {
	return mySugar;
    }

    /**
     * Gets amount of sugar carried by an agent
     * @return sugar carried by agent
     */
    public int getAgentSugar() {
	return myAgentSugar;
    }

    /**
     * Resets the amount of sugar carried by an agent in order to add or metabolize sugar
     * @param sugar
     */
    public void setAgentSugar(int sugar) {
	myAgentSugar = sugar;
    }

    /**
     * Sets amount of sugar on a patch
     * @param sugar
     */
    public void setSugar(int sugar) {
	mySugar = sugar;
    }

    /**
     * Gets maximum amount of sugar allowed on a patch
     * @return maximum sugar amount
     */
    public int getMaxSugar() {
	return myMaxSugar;
    }

    /**
     * Gets the vision of a given agent
     * @return vison of agent
     */
    public int getVision() {
	return myAgentVision;
    }

    /**
     * Sets the vision of an agent, used when an agent moves to a new cell
     * @param vision
     */
    public void setVision(int vision) {
	myAgentVision = vision;
    }

    /**
     * Gets metabolism of a an agent to determine the amount of sugar that should be metabolized
     * @return agent's metabolism
     */
    public int getMetabolism() {
	return myAgentMetabolism;
    }

    /**
     * Sets the metabolism of an agent for when an agent moves to a new cell
     * @param metabolism
     */
    public void setMetabolism(int metabolism) {
	myAgentMetabolism = metabolism;
    }

    @Override
    public void setColors(String[] colors) {
	// TODO Auto-generated method stub
	
    }

}

package simulation.cell;

import javafx.scene.paint.Color;
import simulation.factoryClasses.ColorMapper;

/**
 * Cell object used in Sugar simulation.
 * 
 * @author Michael Acker
 * @author Katherine Van Dyk
 * @date 2/10/18
 *
 */
public class SugarCell extends Cell {

    private final int PATCH = 0;
    private int mySugar;
    private int myMaxSugar;
    private int myAgentSugar;
    private int myAgentMetabolism;
    private int myAgentVision;
    private int myTicks;
    private String[] COLORS;

    /**
     * Sugar cell constructor method.
     * 
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
	COLORS = new ColorMapper().getColors("DefaultSugar");
    }

    /**
     * Colors cell based on current cell state.
     */
    @Override
    public Color colorCell() {
	double gradient = (double) mySugar / (double) myMaxSugar;
	if(myState == PATCH) {
		if (mySugar == 0) {
			return Color.web(COLORS[2]);
		} else return Color.web(COLORS[myState], gradient);
	}
	else {
	    return Color.web(COLORS[myState]);
	}
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
	COLORS = colors;
    }

}

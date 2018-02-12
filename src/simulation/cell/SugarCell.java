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

    private final int PATCH = 1;
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
	if(myState == PATCH) {
	    double gradient = (double) mySugar / (double) myMaxSugar;
	    return Color.web(COLORS[myState], gradient);
	}
	else {
	    double gradient = (double) mySugar / (double) myMaxSugar;
	    return Color.web(COLORS[myState], gradient);
	}
    }

    /**
     * 
     * @return
     */
    public int getTicks() {
	return myTicks;
    }

    /**
     * Resets ticks for current cell
     */
    public void resetTicks() {
	myTicks = 0;
    }

    /**
     * Gets current amount of sugar in cell
     * @return
     */
    public int getSugar() {
	return mySugar;
    }

    public int getAgentSugar() {
	return myAgentSugar;
    }

    public void setAgentSugar(int sugar) {
	myAgentSugar = sugar;
    }

    public void setSugar(int sugar) {
	mySugar = sugar;
    }

    public int getMaxSugar() {
	return myMaxSugar;
    }

    public int getVision() {
	return myAgentVision;
    }

    public void setVision(int vision) {
	myAgentVision = vision;
    }

    public int getMetabolism() {
	return myAgentMetabolism;
    }

    public void setMetabolism(int metabolism) {
	myAgentMetabolism = metabolism;
    }

    @Override
    public void setColors(String[] colors) {
	COLORS = colors;
    }

}

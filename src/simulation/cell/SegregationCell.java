package simulation.cell;

import javafx.scene.paint.Color;

/**
 * Cell object for segregation simulation
 * 
 * @author Katherine Van Dyk
 * @author Ben Hodgson
 */
public class SegregationCell extends Cell {

    private final int GROUP1 = 0;
    private final int GROUP2 = 1;

    /**
     * Constructor for segregation cell
     * 
     * @param x: x position on grid
     * @param y: y position on grid
     * @param state
     */
    public SegregationCell(int x, int y, int state) {
	super(x, y, state); 		
    }

    /**
     * Colors cell based on current state
     * 
     * @param state: current state
     */
    @Override
    public Color colorCell() {
	if (myState == GROUP1) {
	    return Color.THISTLE;
	}
	else if(myState == GROUP2) {
	    return Color.AZURE;
	}
	else {
	    return Color.DIMGRAY;
	}
    }
}

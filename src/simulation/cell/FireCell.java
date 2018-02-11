package simulation.cell;

import javafx.scene.paint.Color;

/**
 * Cell object specific to fire simulation
 * 
 * @author Katherine Van Dyk
 * @author Ben Hodgson
 *
 */
public class FireCell extends Cell {

    private final int TREE = 1;
    private final int BURNING = 2;

    /**
     * Constructor for FIRE cell
     * 
     * @param x: x-location of cell
     * @param y: y-location of cell
     * @param state
     */
    public FireCell(int x, int y, int state) {
	super(x, y, state);
    }

    /**
     * Colors cell based on current state
     */
    public Color colorCell() {
	if (myState == BURNING) {
	    return Color.INDIANRED;
	}
	else if (myState == TREE) {
	    return Color.LIGHTGREEN;
	} 
	else {
	    return Color.DIMGRAY;
	}
    }
}

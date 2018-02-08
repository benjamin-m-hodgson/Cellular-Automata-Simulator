package simulation.cell;

import javafx.scene.paint.Color;

/**
 * Fire Cell object
 * 
 * @author Katherine Van Dyk
 * @author Ben Hodgson
 *
 */
public class FireCell extends Cell {
	
	private int TREE = 1;
	private int BURNING = 2;
	
	/**
	 * Constructor for fire cell
	 * 
	 * @param x
	 * @param y
	 * @param state
	 */
	public FireCell(int x, int y, int state) {
		super(x, y, state);
	}

	/**
	 * Sets current state of fire cell
	 */
	@Override
	public void setState(int state) {
		myPreviousState = myState;
		myNextState = state;
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

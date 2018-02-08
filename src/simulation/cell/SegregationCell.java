package simulation.cell;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Cell object for segregation simulation
 * 
 * @author Katherine Van Dyk
 * @author Ben Hodgson
 */
public class SegregationCell extends Cell {
	
	private int GROUP1 = 0;
	private int GROUP2 = 1;

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
	 * Assigns rectangle to represent segregation cell
	 */
	@Override
	public void drawShape() {
		Rectangle GOLBlock = new Rectangle();
		myShape = GOLBlock;

	}
	
	/**
	 * Sets state of segregation cell
	 * 
	 * @param state: current state
	 */
	@Override
	public void setState(int state) {
		myPreviousState = myState;
		myNextState = state;
	}

	/**
	 * Colors cell based on current state
	 * 
	 * @param state: current state
	 */
	@Override
	public void colorCell(int state) {
		if (myState == GROUP1) {
			myShape.setFill(Color.THISTLE);
		}
		else if(myState == GROUP2) {
			myShape.setFill(Color.AZURE);
		}
		else {
			myShape.setFill(Color.DIMGRAY);
		}
	}
}

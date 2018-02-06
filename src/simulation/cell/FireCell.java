package simulation.cell;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FireCell extends Cell {
	
	private int TREE = 1;
	private int BURNING = 2;
	
	public FireCell(int x, int y, int state) {
		super(x, y, state);
	}

	// assigns a rectangle to represent the FireCell
	@Override
	public void drawShape() {
		Rectangle fireBlock = new Rectangle();
		myShape = fireBlock;
		
	}

	@Override
	public void setState(int state) {
		myPreviousState = myState;
		myNextState = state;
	}	
	
	public void colorCell(int state) {
		if (state == BURNING) {
			myShape.setFill(Color.INDIANRED);
		}
		else if (state == TREE) {
			myShape.setFill(Color.LIGHTGREEN);
		} 
		else {
			myShape.setFill(Color.DIMGRAY);
		}
	}
}

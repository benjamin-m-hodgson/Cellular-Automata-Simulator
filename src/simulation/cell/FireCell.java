package simulation.cell;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FireCell extends Cell {
	
	int BURNING = 0;
	int TREE = 1;
	
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
		myState = state;
		if (myState == BURNING) {
			myShape.setFill(Color.RED);
		}
	}	
}

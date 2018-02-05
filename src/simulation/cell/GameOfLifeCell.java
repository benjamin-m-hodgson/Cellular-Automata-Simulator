package simulation.cell;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameOfLifeCell extends Cell {

	private static final int LIVE = 0;
	
	public GameOfLifeCell(int x, int y, int state) {
		super(x, y, state);
	}

	// assigns a rectangle to represent the FireCell
	@Override
	public void drawShape() {
		Rectangle GOLBlock = new Rectangle();
		myShape = GOLBlock;
		
	}

	@Override
	public void setState(int state) {
		myPreviousState = myState;
		myState = state;
		if (myState == LIVE) {
			myShape.setFill(Color.LIGHTGREEN);
		}
	}
}

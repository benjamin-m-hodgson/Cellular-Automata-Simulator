package simulation.cell;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SegregationCell extends Cell {
	
	private int GROUP1 = 0;
	private int GROUP2 = 1;
	
	private boolean MOVED;
	private boolean SATISFIED;

	public SegregationCell(int x, int y, int state) {
		super(x, y, state); 		
		MOVED = false;
	}


	public void setMove(boolean b) {
		MOVED = b;
	}

	public boolean getMove() {
		return MOVED;
	}

	public void setSatisfaction(boolean b) {
		SATISFIED = b;
	}

	public boolean getSatisfaction() {
		return SATISFIED;
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
		System.out.println(myState);
		if (myState == GROUP1) {
			myShape.setFill(Color.THISTLE);
		}
		else if(myState == GROUP2) {
			myShape.setFill(Color.AZURE);
		}
		else {
			myShape.setFill(Color.rgb(105, 105, 105));
		}
	}


}

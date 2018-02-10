package simulation.cell;

import javafx.scene.shape.Rectangle;

public class RPSCell extends Cell {
	
	private int WHITE = 0;
	private int ROCK = 1;
	private int PAPER = 2;
	private int SCISSORS = 3;
	private int myGradient;
	
	public RPSCell(int x, int y, int state, int gradient) {
		super(x, y, state);
		myGradient = gradient;
	}

	@Override
	public void setState(int state) {
		myPreviousState = myState;
		myNextState = state;
	}
	

	@Override
	public void drawShape() {

	}

	@Override
	public void colorCell(int state) {
		// TODO Need to work with gradients for each color

	}
	
	public int getGradient() {
		return myGradient;
	}
	
	public void setGradient(int gradient) {
		myGradient = gradient;
	}
	
	public void upgrade() {
		if(myGradient > 0) myGradient--;
	}
	
	public void downgrade() {
		if(myGradient < 9) myGradient++;
	}

}

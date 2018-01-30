package cellsociety_team01;

public abstract class Cell {
	private int myState;
	private int myPreviousState;
	private int myXPos;
	private int myYPos;
	
	public void setState(int state) {
		myPreviousState = myState;
		myState = state;
	}
}

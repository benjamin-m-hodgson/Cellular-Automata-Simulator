package simulation.cell;

public abstract class Cell {
	protected int myState;
	protected int myPreviousState;
	protected int myXPos;
	protected int myYPos;
	
	public Cell(int x, int y, int state) {
		myState = state;
		myXPos = x;
		myYPos = y;
	}
	
	public void setState(int state) {
		myPreviousState = myState;
		myState = state;
	}
}

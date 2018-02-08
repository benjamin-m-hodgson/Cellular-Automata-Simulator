package simulation.cell;

import javafx.scene.paint.Color;

/**
 * Cell object to be placed in grid 
 * 
 * @author Katherine Van Dyk
 *
 */
public abstract class Cell implements Cloneable{
	protected int myState;
	protected int myPreviousState;
	protected int myNextState;
	protected int myXPos;
	protected int myYPos;
	protected Boolean MOVED;
	
	/**
	 * Constructor for cell object
	 * 
	 * @param x: x position in grid
	 * @param y: y position in grid
	 * @param state: current state
	 */
	public Cell(int x, int y, int state) {
		myState = state;
		myXPos = x;
		myYPos = y;
		MOVED = false;
	}
	
	/**
	 * Returns x position on grid
	 * 
	 * @return int of array position
	 */
	public int getX() {
		return this.myXPos;
	}
	
	/**
	 * Sets x position on grid
	 * 
	 * @param int of array position
	 */
	public void setX(int x) {
		this.myXPos = x;
	}
	
	/**
	 * Returns y position on grid
	 * 
	 * @param int of array position
	 */
	public int getY() {
		return this.myYPos;
	}
	
	/**
	 * Sets y position on grid
	 * 
	 * @param int of array position
	 */
	public void setY(int y) {
		this.myYPos = y;
	}
	
	/**
	 * Gets state of cell object
	 * 
	 * @return int representing current state
	 */
	public int getState() {
		return myState;
	}

	/**
	 * Makes the next state the current state
	 */
	public void updateState() {
		myState = myNextState;
	}
	
	/**
	 * Takes @param state and updates the state of the Cell along with any
	 * related properties
	 * 
	 * @param state: new state to update
	 */
	public abstract void setState(int state);
	
	/**
	 * Changes the color of the cell to reflect state changes for animation
	 * 
	 * @param state: the current state of the cell
	 */
	public abstract Color colorCell();
	
	/**
	 * Set that cell has moved so that it is not overwritten in later iterations
	 * 
	 * @param b: true if moved, false otherwise
	 */
	public void setMove(boolean b) {
		MOVED = b;
	}

	/**
	 * Returns if cell has been moved during iteration
	 * 
	 * @return boolean: true if it has been moved, false otherwise
	 */
	public boolean getMove() {
		return MOVED;
	}
	
}

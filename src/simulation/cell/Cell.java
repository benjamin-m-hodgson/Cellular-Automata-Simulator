package simulation.cell;

import javafx.scene.shape.Rectangle;

public abstract class Cell implements Cloneable{
	protected int myState;
	protected int myPreviousState;
	protected int myNextState;
	protected int myXPos;
	protected int myYPos;
	protected Rectangle myShape;
	
	public Cell(int x, int y, int state) {
		myState = state;
		myXPos = x;
		myYPos = y;
		drawShape();
		colorCell(myState);
	}
	
	public int getX() {
		return this.myXPos;
	}
	
	public void setX(int x) {
		this.myXPos = x;
	}
	
	public int getY() {
		return this.myYPos;
	}
	
	public void setY(int y) {
		this.myYPos = y;
	}
	
	public int getState() {
		return myState;
	}
	
	public int getPreviousState() {
		return myPreviousState;
	}
	
	/**
	 * Makes the next state the current state
	 */
	public void updateState() {
		myState = myNextState;
		colorCell(myState);
	}
	
	/**
	 * 
	 * @return myShape: the Shape object that visually represents this Cell
	 */
	public Rectangle getShape() {
		if (myShape == null) {
			drawShape();
		}
		return myShape;
	}
	
	/**
	 * Returns a copy of this object using the Cloneable interface
	 */
	public Cell clone() throws CloneNotSupportedException {
		Cell copyCell = (Cell) super.clone();
		copyCell.myXPos = this.myXPos;
		copyCell.myYPos = this.myYPos;
		copyCell.myState = this.myState;
		copyCell.myShape.setFill(this.myShape.getFill());
		return copyCell;
	}
	
	/**
	 * Takes @param state and updates the state of the Cell along with any
	 * related properties
	 * 
	 * @param state: new state to update
	 */
	public abstract void setState(int state);
	
	/**
	 * Method to assign the instance variable myShape to a Shape object
	 * to visually represent the Cell
	 */
	public abstract void drawShape();
	
	/**
	 * Changes the color of the cell to reflect state changes for animation
	 * 
	 * @param state: the current state of the cell
	 */
	public abstract void colorCell(int state);
	
	/**
	 * 
	 * @return Cell[]: an array of Cells neighboring this Cell object
	 */
	public Cell[] getNeighbors() {
		return null;
	}
	
}

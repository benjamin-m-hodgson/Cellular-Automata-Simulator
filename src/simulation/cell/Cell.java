package simulation.cell;

import javafx.scene.shape.Shape;

public abstract class Cell implements Cloneable{
	protected int myState;
	protected int myPreviousState;
	protected int myXPos;
	protected int myYPos;
	protected Shape myShape;
	
	public Cell(int x, int y, int state) {
		myState = state;
		myXPos = x;
		myYPos = y;
	}
	
	public int getX() {
		return this.myXPos;
	}
	
	public int getY() {
		return this.myYPos;
	}
	
	public int getState() {
		return myState;
	}
	
	public int getPreviousState() {
		return myPreviousState;
	}
	
	/**
	 * 
	 * @return myShape: the Shape object that visually represents this Cell
	 */
	public Shape getShape() {
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
	 * 
	 * @return Cell[]: an array of Cells neighboring this Cell object
	 */
	public Cell[] getNeighbors() {
		return null;
	}
}

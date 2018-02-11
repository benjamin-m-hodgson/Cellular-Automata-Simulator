package simulation.cell;

import javafx.scene.paint.Color;

/**
 * Cell superclass creates a cell object to be placed at position (x,y) in the grid. 
 * A cell knows its previous, next and current state, and whether or not it has been 
 * moved/moved to in the current generation.
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
     * @return int: x-array position of cell on grid
     */
    public int getX() {
	return this.myXPos;
    }

    /**
     * Sets x position of cell on grid to @param x
     */
    public void setX(int x) {
	this.myXPos = x;
    }

    /**
     * @return int: y-array position of cell on grid
     */
    public int getY() {
	return this.myYPos;
    }

    /**
     * Sets x position of cell on grid to @param x
     */
    public void setY(int y) {
	this.myYPos = y;
    }

    /**
     * @return int representing current state of cell object
     */
    public int getState() {
	return myState;
    }

    /**
     * Updates the states in the cell array- makes the current state equal to the next (calculated) state
     */
    public void updateState() {
	myState = myNextState;
    }

    /**
     * Takes @param state and updates the state of the Cell along with any
     * related properties
     */
    public void setState(int state) {
	myPreviousState = myState;
	myNextState = state;
    }

    /**
     * Changes the color of the cell to reflect the new @param state of the cell
     */
    public abstract Color colorCell();

    /**
     * Set that cell has moved to so that it is not overwritten in later iterations
     * 
     * @param b: true if moved, false otherwise
     */
    public void setMove(boolean b) {
	MOVED = b;
    }

    /**
     * @return boolean: true if it has been moved during the current iteration, false otherwise
     */
    public boolean getMove() {
	return MOVED;
    }
}

package simulation.cell;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Cell object for WaTor simulation
 * 
 * @author Katherine Van Dyk
 * @author Ben Hodgson
 *
 */
public class WaTorCell extends Cell {
	
	private boolean MOVED;
	private int ENERGY;
	private int ENERGY_HOLDER;
	private int BREEDINGTIME;
	private int FISH = 0;
	private int SHARK = 1; 
	private int VACANT = 2;

	/**
	 * Constructor for WaTor cell
	 * 
	 * @param x
	 * @param y
	 * @param state
	 * @param initEnergy
	 */
	public WaTorCell(int x, int y, int state, int initEnergy) {
		super(x, y, state);
		MOVED = false;
		ENERGY = initEnergy;
		ENERGY_HOLDER = initEnergy;
		BREEDINGTIME = 0;
	}
	
	/**
	 * Increments energy of WaTor cell
	 */
	public void incrementEnergy() {
		ENERGY = ENERGY + 2;
	}
	
	/**
	 * Decrements energy of WaTor cell
	 */
	public void decrementEnergy() {
		ENERGY--;
	}
	
	/**
	 * Increases breeding time of WaTor cell
	 */
	public void incrementBreedingTime() {
		BREEDINGTIME++;
	}
	
	/**
	 * Gets breeding time of WaTor cell
	 * 
	 * @return Breeding time (to see if fish is ready to birth)
	 */
	public int getBreedingTime() {
		return BREEDINGTIME;
	}
	
	/**
	 * Sets breeding time of WaTor cell
	 * 
	 * @param time: Time until fish can give birth
	 */
	public void setBreedingTime(int time) {
		BREEDINGTIME = time;
	}
	
	public int getNextState() {
		return myNextState;
	}
	
	/**
	 * Gets energy of WaTor cell
	 * 
	 * @return int representing energy of shark cell
	 */
	public int getEnergy() {
		return ENERGY;
	}
	
	/**
	 * Gets energy of WaTor cell
	 * 
	 * @param e: int representing energy of WaTor cell
	 */
	public void setEnergy(int e) {
		ENERGY = e;
	}
	
	/**
	 * Resets cell to initial parameters
	 */
	public void reset() {
		this.BREEDINGTIME = 0;
		this.ENERGY = ENERGY_HOLDER;
	}
	
	/**
	 * Sets if WaTor cell has moved in current simulation
	 * 
	 * @param b: true if cell has moved
	 */
	public void setMove(boolean b) {
		this.MOVED = b;
	}
	
	/**
	 * Gets if WaTor cell has moved during current simulation
	 * 
	 * @return true if has moved, false otherwise
	 */
	public boolean getMove() {
		return this.MOVED;
	}

	/**
	 * Creates rectangle representing WaTor cell
	 */
	@Override
	public void drawShape() {
		Rectangle watBlock = new Rectangle();
		myShape = watBlock;
	}

	/**
	 * Sets current state of WaTor cell
	 */
	@Override
	public void setState(int state) {
		myPreviousState = myState;
		myNextState = state;
	}

	/**
	 * Colors cell based on current state
	 */
	@Override
	public void colorCell(int state) {
		if (myState == SHARK) {
			myShape.setFill(Color.DEEPSKYBLUE);
		}
		else if (myState == FISH) {
			myShape.setFill(Color.POWDERBLUE);
		}
		else {
			myShape.setFill(Color.DIMGRAY);
		}
	}	
}

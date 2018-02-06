package simulation.cell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Fire Cell object
 * 
 * @author Katherine Van Dyk
 * @author Ben Hodgson
 *
 */
public class FireCell extends Cell {
	
	private int TREE = 1;
	private int BURNING = 2;
	
	/**
	 * Constructor for fire cell
	 * 
	 * @param x
	 * @param y
	 * @param state
	 */
	public FireCell(int x, int y, int state) {
		super(x, y, state);
	}

	/**
	 * Assigns rectangle to represent fire cell
	 */
	@Override
	public void drawShape() {
		Rectangle fireBlock = new Rectangle();
		myShape = fireBlock;
		
	}

	/**
	 * Sets current state of fire cell
	 */
	@Override
	public void setState(int state) {
		myPreviousState = myState;
		myNextState = state;
	}	
	
	/**
	 * Colors cell based on current state
	 */
	public void colorCell(int state) {
		if (state == BURNING) {
			myShape.setFill(Color.INDIANRED);
		}
		else if (state == TREE) {
			myShape.setFill(Color.LIGHTGREEN);
		} 
		else {
			myShape.setFill(Color.DIMGRAY);
		}
	}
}

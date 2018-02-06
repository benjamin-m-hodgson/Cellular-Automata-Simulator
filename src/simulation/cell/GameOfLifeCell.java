package simulation.cell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Cell object for Game of Life simulation
 * 
 * @author Katherine Van Dyk
 * @author Ben Hodgson
 *
 */
public class GameOfLifeCell extends Cell {

	private static final int LIVE = 0;
	
	/**
	 * Constructor for Game of Life Cell
	 * 
	 * @param x
	 * @param y
	 * @param state
	 */
	public GameOfLifeCell(int x, int y, int state) {
		super(x, y, state);
	}

	/**
	 * Assigns rectangle to represent Game of Life Cell
	 */
	@Override
	public void drawShape() {
		Rectangle GOLBlock = new Rectangle();
		myShape = GOLBlock;
		
	}

	/**
	 * Sets current state of Game of Life cell
	 */
	@Override
	public void setState(int state) {
		myPreviousState = myState;
		myNextState = state;
	}

	/**
	 * Assigns color of Game of Life cell based on current state
	 */
	@Override
	public void colorCell(int state) {
		if (state == LIVE) {
			myShape.setFill(Color.LIGHTGREEN);
		}
	}
}

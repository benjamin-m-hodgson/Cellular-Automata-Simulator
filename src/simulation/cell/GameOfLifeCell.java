package simulation.cell;
import javafx.scene.paint.Color;


/**
 * Cell object for Game of Life simulation
 * 
 * @author Katherine Van Dyk
 * @author Ben Hodgson
 *
 */
public class GameOfLifeCell extends Cell {

    private final int LIVE = 0;

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
     * Assigns color of Game of Life cell based on current state
     */
    @Override
    public Color colorCell() {
	if (myState == LIVE) {
	    return Color.LIGHTGREEN;
	}
	return Color.DIMGRAY;
    }
}

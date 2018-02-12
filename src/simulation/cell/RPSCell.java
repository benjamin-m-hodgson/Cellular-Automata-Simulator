package simulation.cell;
import javafx.scene.paint.Color;

/**
 * 
 * Cell object for Rock, Paper, Scissors simulation
 *
 */

public class RPSCell extends Cell {

    private int WHITE = 0;
    private int ROCK = 1;
    private int PAPER = 2;
    private int SCISSORS = 3;
    private int myGradient;

    /**
     * Constructor for RPS cell
     * @param x
     * @param y
     * @param state
     * @param gradient
     */
    public RPSCell(int x, int y, int state, int gradient) {
	super(x, y, state);
	myGradient = gradient;
    }

    /**
     * Determines cell color and gradient
     */
    @Override
    public Color colorCell() {
	double gradient = 1.0;
	if (myGradient != 0) {
	    gradient = 1.0 / ((double) myGradient + 1.0);
	}
	if (myState == WHITE) {
	    return Color.WHITE;
	} else if (myState == ROCK) {
	    return Color.web("#FF0000", gradient);
	} else if (myState == PAPER) {
	    return Color.web("#008000", gradient);
	} else if (myState == SCISSORS) {
	    return Color.web("#0000FF", gradient);
	} else return Color.BLACK;
    }

    public int getGradient() {
	return myGradient;
    }

    public void setGradient(int gradient) {
	myGradient = gradient;
    }

    /**
     * Upgrades gradient of a cell to a higher power
     */
    public void upgrade() {
	if(myGradient > 0) myGradient--;
    }

    /**
     * Downgrades gradient of a cell to a lower power
     */
    public void downgrade() {
	if(myGradient < 9) myGradient++;
    }

}

package simulation.cell;
import javafx.scene.paint.Color;

public class RPSCell extends Cell {

    private int WHITE = 0;
    private int ROCK = 1;
    private int PAPER = 2;
    private int SCISSORS = 3;
    private int myGradient;

    public RPSCell(int x, int y, int state, int gradient) {
	super(x, y, state);
	myGradient = gradient;
    }

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

    public void upgrade() {
	if(myGradient > 0) myGradient--;
    }

    public void downgrade() {
	if(myGradient < 9) myGradient++;
    }

}

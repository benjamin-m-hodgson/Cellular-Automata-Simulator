package simulation.cell;
import javafx.scene.paint.Color;
import simulation.factoryClasses.ColorMapper;

/**
 * 
 * Cell object for Rock, Paper, Scissors simulation
 *
 */

public class RPSCell extends Cell {

    private String[] COLORS;
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
	COLORS = new ColorMapper().getColors("DefaultRPS");
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
	return Color.web(COLORS[myState], gradient);
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

    @Override
    public void setColors(String[] colors) {
	COLORS = colors;
    }
}

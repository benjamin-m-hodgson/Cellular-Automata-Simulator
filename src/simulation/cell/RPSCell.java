package simulation.cell;
import javafx.scene.paint.Color;
import simulation.factoryClasses.ColorMapper;

public class RPSCell extends Cell {

    private String[] COLORS;
    private int myGradient;

    public RPSCell(int x, int y, int state, int gradient) {
	super(x, y, state);
	myGradient = gradient;
	COLORS = new ColorMapper().getColors("DefaultRPS");
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

    @Override
    public void setColors(String[] colors) {
	COLORS = colors;
    }
   
    @Override
    public Color colorCell() {
	double gradient = 1.0;
	if (myGradient != 0) {
	    gradient = 1.0 / ((double) myGradient + 1.0);
	}
	return Color.web(COLORS[myState], gradient);
    }

}

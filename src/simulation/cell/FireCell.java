package simulation.cell;

import javafx.scene.paint.Color;
import simulation.factoryClasses.ColorMapper;

/**
 * Cell object specific to fire simulation
 * 
 * @author Katherine Van Dyk
 * @author Ben Hodgson
 *
 */
public class FireCell extends Cell {

    private String[] COLORS;

    /**
     * Constructor for FIRE cell
     * 
     * @param x: x-location of cell
     * @param y: y-location of cell
     * @param state
     */
    public FireCell(int x, int y, int state) {
	super(x, y, state);
	COLORS = new ColorMapper().getColors("DefaultFire");
    }
 

    @Override
    public Color colorCell() {
	return Color.web(COLORS[myState]);
    }


    @Override
    public void setColors(String[] colors) {
	if(colors != null) {
	    COLORS = colors;
	}
	
    }
}

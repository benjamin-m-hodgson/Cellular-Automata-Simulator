package simulation.cell;

import javafx.scene.paint.Color;
import simulation.factoryClasses.ColorMapper;

/**
 * Cell object for segregation simulation
 * 
 * @author Katherine Van Dyk
 * @author Ben Hodgson
 */
public class SegregationCell extends Cell {

    private String[] COLORS;
    

    /**
     * Constructor for segregation cell
     * 
     * @param x: x position on grid
     * @param y: y position on grid
     * @param state
     */
    public SegregationCell(int x, int y, int state) {
	super(x, y, state); 		
	COLORS = new ColorMapper().getColors("DefaultSegregation");
    }

    @Override
    public Color colorCell() {
	return Color.web(COLORS[myState]);
    }
    
    @Override
    public void setColors(String[] color) {
	COLORS = color;
    }
}

package simulation.cell;
import javafx.scene.paint.Color;
import simulation.factoryClasses.ColorMapper;


/**
 * Cell object for Game of Life simulation
 * 
 * @author Katherine Van Dyk
 * @author Ben Hodgson
 *
 */
public class GameOfLifeCell extends Cell {

    private String[] COLORS;

    /**
     * Constructor for Game of Life cell
     * 
     * @param x: x-location of cell
     * @param y: y-location of cell
     * @param state
     */
    public GameOfLifeCell(int x, int y, int state) {
	super(x, y, state);
	COLORS = new ColorMapper().getColors("DefaultGameOfLife");
    }

    /**
     * Assigns color of Game of Life cell based on current state
     */
    @Override
    public Color colorCell() {
	return Color.web(COLORS[myState]);
    }
    
    @Override
    public void setColors(String[] color) {
	COLORS = color;
    }
}

package configuration.datatemplates;

import simulation.cell.*;
import simulation.grid.*;
import simulation.ruleSet.*;

/**
 * Use this class to parse Game of Life-simulation type XML files. This class is dependent on the 
 * superclass XMLData for parsing standard (not parameter-specific) data. 
 * 
 * @author Katherine Van Dyk
 * @date 2/1/18
 *
 */
public class GameOfLifeXMLData extends XMLData {
    private final String GAMEOFLIFE = "GameOfLife";   
    private String[] PARAMETERS;

    /** 
     * Constructor for GameOfLife XML data template
     */
    public GameOfLifeXMLData() {
	super();
	PARAMETERS = getParameters(GAMEOFLIFE);
    }

    /**
     * Returns Game of Life ruleset object initialized with parameters in XML file
     */
    @Override
    public GameOfLifeRuleset getRules() {
	int minLife = Integer.parseInt(myDataValues.get(PARAMETERS[0]));
	int maxLife = Integer.parseInt(myDataValues.get(PARAMETERS[1]));
	int birth = Integer.parseInt(myDataValues.get(PARAMETERS[2]));
	return new GameOfLifeRuleset(minLife, maxLife, birth);
    }

    /**
     * Returns grid object with cells initialized to states in XML file
     */
    public Grid getGrid(int[][] states) {
	Grid g = new StandardGrid(GAMEOFLIFE, this.getXSize(), this.getYSize());
	for(int r= 0; r < this.getXSize(); r++) {
	    for(int c = 0; c < this.getYSize(); c++) {
		g.addCell(new GameOfLifeCell(r, c, states[r][c]));
	    }
	}
	return g;
    }

    /**
     * Returns grid object
     */
    @Override
    public Grid getGrid() {
	int[][] states  = getStates(2);
	return getGrid(states);
    }
}

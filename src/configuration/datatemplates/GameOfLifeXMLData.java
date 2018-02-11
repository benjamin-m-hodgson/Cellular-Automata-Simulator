package configuration.datatemplates;
import java.util.Arrays;
import java.util.List;

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
    private final String GAMEOFLIFE = "Game of Life";
    private static final List<String> PARAM_DATA_FIELDS = Arrays.asList(new String[] {
	    "minLife",
	    "maxLife",
	    "birth"
    });

    /** 
     * Constructor for GameOfLife XML data template
     */
    public GameOfLifeXMLData() {
	super();
    }

    /**
     * Returns simulation specific parameters
     */
    @Override
    public List<String> getParameterFields() {
	return PARAM_DATA_FIELDS;
    }

    /**
     * All data fields (general and simulation specific)
     */
    @Override
    public List<String> getDataFields() {
	List<String> result = getStandardFields();
	result.addAll(PARAM_DATA_FIELDS);
	return result;
    }

    /**
     * Returns Game of Life ruleset object initialized with parameters in XML file
     */
    @Override
    public GameOfLifeRuleset getRules() {
	int minLife = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(0)));
	int maxLife = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(1)));
	int birth = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(2)));
	return new GameOfLifeRuleset(minLife, maxLife, birth);
    }

    /**
     * Returns grid object with cells initialized to states in XML file
     */
    public Grid getGrid(int[][] states) {
	Grid g = new StandardGrid(this.getXSize(), this.getYSize());
	g.setType(GAMEOFLIFE);
	for(int r= 0; r < this.getXSize(); r++) {
	    for(int c = 0; c < this.getYSize(); c++) {
		g.addCell(new GameOfLifeCell(r, c, states[r][c]));
	    }
	}
	return g;
    }

}

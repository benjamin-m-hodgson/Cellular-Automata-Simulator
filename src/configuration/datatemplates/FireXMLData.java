package configuration.datatemplates;

import simulation.cell.*;
import simulation.grid.*;
import simulation.ruleSet.*;

/**
 * Use this class to parse Fire-simulation type XML files. This class is dependent on the 
 * superclass XMLData for parsing standard (not parameter-specific) data. The XMLDataFactory class
 * chooses this template from a Hashmap based on simulation type (which is initialized in the XMLParser file).
 * 
 * @author Katherine Van Dyk
 * @date 2/1/18
 */
public class FireXMLData extends XMLData {
    private final String FIRE = "Fire";
    private final int MAX_STATES = 3;
    private String[] PARAMETERS;

    /**
     * Constructor for FireXML Data subclass. Obtains fire-specific parameters from resource file.
     */
    public FireXMLData() {
	super();
	PARAMETERS = getParameters(FIRE);
    }

    /**
     * Obtains simulation-specific parameters from myDataValues hash map and inserts into rule set constructor
     * @return FireRuleset: Ruleset object specific to Fire simulation
     */
    @Override
    public FireRuleset getRules() {
	return new FireRuleset(Double.parseDouble(myDataValues.get(PARAMETERS[0])));
    }

    /**
     * Returns grid object
     */
    @Override
    public Grid getGrid() {
	int[][] states  = getStates(MAX_STATES);
	return getGrid(states);
    }

    /**
     * Helper method for constructing cell objects from initial states array, and adding cells to form a grid object
     * @param states: integer array of initial cell states obtained from getStates() method
     * @return Grid: Standard grid object containing FireCells initialized to correct starting state
     */
    private Grid getGrid(int[][] states) {
	Grid g = new StandardGrid(FIRE, this.getXSize(), this.getYSize());
	for (int r= 0; r < this.getXSize(); r++) {
	    for(int c = 0; c < this.getYSize(); c++) {
		g.addCell(new FireCell(r, c, states[r][c]));
	    }
	}
	return g;
    }
}

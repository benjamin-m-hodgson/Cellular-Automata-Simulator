package configuration.datatemplates;

import simulation.cell.*;
import simulation.grid.Grid;
import simulation.grid.StandardGrid;
import simulation.ruleSet.*;

/**
 * Use this class to parse Segregation-simulation type XML files. This class is dependent on the 
 * superclass XMLData for parsing standard (not parameter-specific) data. 
 * 
 * @author Katherine Van Dyk
 * @date 2/1/18
 *
 */
public class SegregationXMLData extends XMLData {
    private final String SEGREGATION = "Segregation";
    private String[] PARAMETERS;
    
    /**
     * Constructor for Segregation data template
     */
    public SegregationXMLData() {
	super();
	PARAMETERS = getParameters(SEGREGATION);
    }
    
    /**
     * Returns Segregation ruleset object initialized with parameters in XML file
     */
    @Override
    public SegregationRuleset getRules() {
	return new SegregationRuleset(Double.parseDouble(myDataValues.get(PARAMETERS[0])));
    }

    /**
     * Returns grid object
     */
    @Override
    public Grid getGrid() {
	int[][] states  = getStates(3);
	return getGrid(states);
    }
    
    /**
     * Returns grid object with cells initialized to states in XML file
     */
    public Grid getGrid(int[][] states) {
	Grid g = new StandardGrid(SEGREGATION, this.getXSize(), this.getYSize());
	for(int r= 0; r < this.getXSize(); r++) {
	    for(int c = 0; c < this.getYSize(); c++) {
		g.addCell(new SegregationCell(r, c, states[r][c]));
	    }
	}
	return g;
    }
}

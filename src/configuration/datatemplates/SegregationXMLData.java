package configuration.datatemplates;
import java.util.Arrays;
import java.util.List;

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
    private static final List<String> PARAM_DATA_FIELDS = Arrays.asList(new String[] {
	    "tolerance"
    });

    /**
     * Constructor for Segregation data template
     */
    public SegregationXMLData() {
	super();
    }

    /**
     * Returns simulation-specific (parameter) data fields
     */
    @Override
    public List<String> getParameterFields() {
	return PARAM_DATA_FIELDS;
    }

    /**
     * Returns all Fire XML data fields (standard and simulation-specific)
     */
    @Override
    public List<String> getDataFields() {
	List<String> result = getStandardFields();
	result.addAll(PARAM_DATA_FIELDS);
	return result;
    }

    /**
     * Returns Segregation ruleset object initialized with parameters in XML file
     */
    @Override
    public SegregationRuleset getRules() {
	return new SegregationRuleset(Double.parseDouble(myDataValues.get(PARAM_DATA_FIELDS.get(0))));
    }

    /**
     * Returns grid object with cells initialized to states in XML file
     */
    @Override
    public Grid getGrid(int[][] states) {
	Grid g = new StandardGrid(this.getXSize(), this.getYSize());
	g.setType(SEGREGATION);
	for(int r= 0; r < this.getXSize(); r++) {
	    for(int c = 0; c < this.getYSize(); c++) {
		g.addCell(new SegregationCell(r, c, states[r][c]));
	    }
	}
	return g;
    }

}

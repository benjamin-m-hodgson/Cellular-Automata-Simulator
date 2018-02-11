package configuration.datatemplates;
import java.util.Arrays;
import java.util.List;

import simulation.cell.*;
import simulation.grid.*;
import simulation.ruleSet.*;

/**
 * Use this class to parse Fire-simulation type XML files. This class is dependent on the 
 * superclass XMLData for parsing standard (not parameter-specific) data. 
 * 
 * @author Katherine Van Dyk
 * @date 2/1/18
 *
 */
public class FireXMLData extends XMLData {
    private final String FIRE = "Fire";
    private static final List<String> PARAM_DATA_FIELDS = Arrays.asList(new String[] {
	    "probCatch"
    });

    /**
     * Constructor for FireXML Data subclass
     */
    public FireXMLData() {
	super();
    }

    /**
     * Gets parameter-specific fields for Fire ruleset
     */
    @Override
    public List<String> getParameterFields() {
	return PARAM_DATA_FIELDS;
    }

    /**
     * Returns all Fire XML data fields (standard and parameter-specific)
     */
    @Override
    public List<String> getDataFields() {
	List<String> result = getStandardFields();
	result.addAll(PARAM_DATA_FIELDS);
	return result;
    }

    /**
     * Returns Fire Ruleset based on parameters from input XML file
     */
    @Override
    public FireRuleset getRules() {
	return new FireRuleset(Double.parseDouble(myDataValues.get(PARAM_DATA_FIELDS.get(0))));
    }

    /**
     * Returns grid object that contains cells with initial states as specified in XML file
     */
    public Grid getGrid(int[][] states) {
	Grid g = new StandardGrid(this.getXSize(), this.getYSize());
	g.setType(FIRE);
	for(int r= 0; r < this.getXSize(); r++) {
	    for(int c = 0; c < this.getYSize(); c++) {
		g.addCell(new FireCell(r, c, states[r][c]));
	    }
	}
	return g;
    }
}

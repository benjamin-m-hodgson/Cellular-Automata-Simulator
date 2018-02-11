package configuration.datatemplates;

import java.util.Arrays;
import java.util.List;

import simulation.cell.*;
import simulation.grid.Grid;
import simulation.grid.StandardGrid;
import simulation.ruleSet.*;

/**
 * Use this class to parse WaTor-simulation type XML files. This class is dependent on the 
 * superclass XMLData for parsing standard (not parameter-specific) data. 
 * 
 * @author Katherine Van Dyk
 * @date 2/1/18
 *
 */
public class WaTorXMLData extends XMLData {
    private final String WATOR = "WaTor";
    private final int FISH = 0;
    private final int SHARK = 1; 
    private final int NOENERGY = 0;
    private static final List<String> PARAM_DATA_FIELDS = Arrays.asList(new String[] {
	    "fishBreedTime",
	    "fishEnergy",
	    "sharkInitEnergy",
	    "sharkBreedEnergy",
    });

    /**
     * Constructor for WaTor XML Data template
     */
    public WaTorXMLData() {
	super();
    }

    /**
     * Returns data fields
     */
    @Override
    public List<String> getParameterFields() {
	return PARAM_DATA_FIELDS;
    }

    /**
     * Returns Fire XML data fields 
     */
    @Override
    public List<String> getDataFields() {
	List<String> result = getStandardFields();
	result.addAll(PARAM_DATA_FIELDS);
	return result;
    }
    
    /**
     * Returns WaTor ruleset object initialized with parameters in XML file
     */
    @Override
    public Ruleset getRules() {
	int fishBreedTime = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(0)));
	int fishInitEnergy = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(1)));
	int sharkInitEnergy = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(2)));
	int sharkBreedTime = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(3)));
	return new WaTorRuleset(fishBreedTime, sharkBreedTime, fishInitEnergy, sharkInitEnergy);
    }
    
    /**
     * Returns WaTor grid object with cells initialized to data in XML file
     */
    @Override
    public Grid getGrid(int[][] states) {
	Grid g = new StandardGrid(this.getXSize(), this.getYSize());
	g.setType(WATOR);
	int sharkEnergy = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(2)));
	int fishEnergy = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(1)));
	for(int r= 0; r < this.getXSize(); r++) {
	    for(int c = 0; c < this.getYSize(); c++) {
		if(states[r][c] == FISH) {
		    g.addCell(new WaTorCell(r, c, states[r][c], fishEnergy));
		}
		else if(states[r][c] == SHARK) {
		    g.addCell(new WaTorCell(r, c, states[r][c], sharkEnergy));
		}
		else {
		    g.addCell( new WaTorCell(r, c, states[r][c], NOENERGY));
		}
	    }
	}
	return g;
    }
}

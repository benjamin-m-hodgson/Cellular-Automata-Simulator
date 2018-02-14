package configuration.datatemplates;

import configuration.XMLParsing.CellStateGenerator;
import simulation.grid.Grid;
import simulation.cell.SugarCell;
import simulation.grid.StandardGrid;
import simulation.ruleSet.Ruleset;
import simulation.ruleSet.SugarRuleset;

/**
 * 
 * Used to parse XML data files for SugarScape simulations.
 * 
 * @author Michael Acker
 * @author Katherine Van Dyk
 * @date 2/11/18
 *
 */
public class SugarXMLData extends XMLData {

    private final String SUGAR = "SugarScape";
    private String[] PARAMETERS;

    /**
     * Constructor
     */
    public SugarXMLData() {
	super();
	PARAMETERS = getParameters(SUGAR);
    }

    /**
     * Gets Ruleset object from XML parser
     */
    @Override
    public Ruleset getRules() {
	int regenRate = Integer.parseInt(myDataValues.get(PARAMETERS[0]));
	int regenInterval = Integer.parseInt(myDataValues.get(PARAMETERS[1]));
	return new SugarRuleset(regenRate, regenInterval);
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
     * Creates grid object from XML parser data
     */
    public Grid getGrid(int[][] states) {
	Grid g = new StandardGrid(SUGAR, this.getXSize(), this.getYSize());
	String sugar = myDataValues.get(PARAMETERS[2]);
	int[][] sugarStates = new CellStateGenerator().locationStates(sugar, getXSize(), getYSize());
	String maxSugar = myDataValues.get(PARAMETERS[3]);
	int[][] maxSugarStates = new CellStateGenerator().locationStates(maxSugar, getXSize(), getYSize());
	String agentSugar = myDataValues.get(PARAMETERS[4]);
	int[][] agentSugarStates = new CellStateGenerator().locationStates(agentSugar, getXSize(), getYSize());
	String agentMetabolism = myDataValues.get(PARAMETERS[5]);
	int[][] agentMetabolismStates = new CellStateGenerator().locationStates(agentMetabolism, getXSize(), getYSize());
	String agentVision = myDataValues.get(PARAMETERS[6]);
	int[][] agentVisionStates = new CellStateGenerator().locationStates(agentVision, getXSize(), getYSize());
	for(int r= 0; r < this.getXSize(); r++) {
	    for(int c = 0; c < this.getYSize(); c++) {
		g.addCell(new SugarCell(r, c, states[r][c], sugarStates[r][c], maxSugarStates[r][c], agentSugarStates[r][c], agentMetabolismStates[r][c], agentVisionStates[r][c]));
	    }
	}
	return g;
    }
    
}

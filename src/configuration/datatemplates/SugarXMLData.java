package configuration.datatemplates;

import java.util.Arrays;
import java.util.List;

import configuration.XMLParsing.CellStateGenerator;
import simulation.grid.Grid;
import simulation.cell.SugarCell;
import simulation.grid.StandardGrid;
import simulation.ruleSet.Ruleset;
import simulation.ruleSet.SugarRuleset;


public class SugarXMLData extends XMLData {
	
	private final String SUGAR = "SugarScape";
	protected static final List<String> PARAM_DATA_FIELDS = Arrays.asList(new String[] {
			"regenRate",
			"regenInterval",
			"sugar",
			"maxSugar",
			"agentSugar",
			"agentMetabolism",
			"agentVision"
	});

	/**
	 * Constructor
	 */
	public SugarXMLData() {
		super();
	}

	/**
	 * Gets Ruleset object from XML parser
	 */
	@Override
	public Ruleset getRules() {
		int regenRate = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(0)));
		int regenInterval = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(1)));
		return new SugarRuleset(regenRate, regenInterval);
	}
	
	/**
	 * Returns data fields
	 */
	@Override
	public List<String> getParameterFields() {
		return PARAM_DATA_FIELDS;
	}
	
	/**
	 * Returns Sugar XML data fields 
	 */
	@Override
	public List<String> getDataFields() {
		List<String> result = getStandardFields();
		result.addAll(PARAM_DATA_FIELDS);
		return result;
	}
	
	/**
	 * Creates grid object from XML parser data
	 */
	@Override
	public Grid getGrid(int[][] states) {
		Grid g = new StandardGrid(this.getXSize(), this.getYSize());
		g.setType(SUGAR);
		String sugar = myDataValues.get(PARAM_DATA_FIELDS.get(2));
		int[][] sugarStates = new CellStateGenerator().locationStates(sugar, getXSize(), getYSize());
		String maxSugar = myDataValues.get(PARAM_DATA_FIELDS.get(3));
		int[][] maxSugarStates = new CellStateGenerator().locationStates(maxSugar, getXSize(), getYSize());
		String agentSugar = myDataValues.get(PARAM_DATA_FIELDS.get(4));
		int[][] agentSugarStates = new CellStateGenerator().locationStates(agentSugar, getXSize(), getYSize());
		String agentMetabolism = myDataValues.get(PARAM_DATA_FIELDS.get(5));
		int[][] agentMetabolismStates = new CellStateGenerator().locationStates(agentMetabolism, getXSize(), getYSize());
		String agentVision = myDataValues.get(PARAM_DATA_FIELDS.get(6));
		int[][] agentVisionStates = new CellStateGenerator().locationStates(agentVision, getXSize(), getYSize());
		for(int r= 0; r < this.getXSize(); r++) {
			for(int c = 0; c < this.getYSize(); c++) {
				g.addCell(new SugarCell(r, c, states[r][c], sugarStates[r][c], maxSugarStates[r][c], agentSugarStates[r][c], agentMetabolismStates[r][c], agentVisionStates[r][c]));
			}
		}
		return g;
	}
}

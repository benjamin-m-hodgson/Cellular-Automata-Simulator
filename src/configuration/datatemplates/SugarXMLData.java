package configuration.datatemplates;

import java.util.Arrays;
import java.util.List;

import simulation.grid.Grid;
import simulation.cell.SugarCell;
import simulation.grid.StandardGrid;
import simulation.ruleSet.Ruleset;
import simulation.ruleSet.SugarRuleset;


public class SugarXMLData extends XMLData {
	
	private final String SUGAR = "SugarScape";
	private final int PATCH = 0;
	private final int AGENT = 1; 
	protected static final List<String> PARAM_DATA_FIELDS = Arrays.asList(new String[] {
			"regenRate",
			"regenInterval"
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
		int sugar = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(0)));
		int maxSugar = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(0)));
		for(int r= 0; r < this.getXSize(); r++) {
			for(int c = 0; c < this.getYSize(); c++) {
				g.addCell(new SugarCell(r, c, states[r][c], sugar, maxSugar));
			}
		}
		return g;
	}
}

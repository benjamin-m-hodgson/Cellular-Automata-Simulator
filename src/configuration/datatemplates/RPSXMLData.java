package configuration.datatemplates;

import java.util.Arrays;
import java.util.List;

import configuration.XMLParsing.CellStateGenerator;
import simulation.grid.Grid;
import simulation.cell.RPSCell;
import simulation.grid.StandardGrid;
import simulation.ruleSet.RPSRuleset;
import simulation.ruleSet.Ruleset;


public class RPSXMLData extends XMLData {
	
	private final String RPS = "Rock, Paper, Scissors";
	protected static final List<String> PARAM_DATA_FIELDS = Arrays.asList(new String[] {
		"gradient"
	});

	/**
	 * Constructor
	 */
	public RPSXMLData() {
		super();
	}

	/**
	 * Gets Ruleset object from XML parser
	 */
	@Override
	public Ruleset getRules() {
	    	return new RPSRuleset();
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
		g.setType(RPS);
		String gradient = myDataValues.get(PARAM_DATA_FIELDS.get(0));
		int[][] gradientStates = new CellStateGenerator().locationStates(gradient, getXSize(), getYSize());
		for(int r= 0; r < this.getXSize(); r++) {
			for(int c = 0; c < this.getYSize(); c++) {
				g.addCell(new RPSCell(r, c, states[r][c], gradientStates[r][c]));
			}
		}
		return g;
	}
}

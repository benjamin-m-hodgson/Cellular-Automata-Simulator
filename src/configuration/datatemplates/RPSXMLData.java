package configuration.datatemplates;

import java.util.Arrays;
import java.util.List;

import simulation.grid.Grid;
import simulation.cell.RPSCell;
import simulation.grid.StandardGrid;
import simulation.ruleSet.RPSRuleset;
import simulation.ruleSet.Ruleset;


public class RPSXMLData extends XMLData {
	
	private final String RPS = "RockPaperScissors";
	private final int WHITE = 0;
	private final int ROCK = 1;
	private final int PAPER = 2;
	private final int SCISSORS = 3;
	protected static final List<String> PARAM_DATA_FIELDS = Arrays.asList(new String[] {
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
		int gradient = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(0)));
		for(int r= 0; r < this.getXSize(); r++) {
			for(int c = 0; c < this.getYSize(); c++) {
				g.addCell(new RPSCell(r, c, states[r][c], gradient));
			}
		}
		return g;
	}
}

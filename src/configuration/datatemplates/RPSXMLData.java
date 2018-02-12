package configuration.datatemplates;

import java.util.Arrays;
import java.util.List;

import configuration.XMLParsing.CellStateGenerator;
import simulation.grid.Grid;
import simulation.cell.RPSCell;
import simulation.grid.StandardGrid;
import simulation.ruleSet.RPSRuleset;
import simulation.ruleSet.Ruleset;

/**
 * Used to parse XML data files for Rock, Paper, Scissors simulations.
 * 
 * @author Michael Acker
 * @author Katherine Van Dyk
 * @date 2/11/18
 *
 */
public class RPSXMLData extends XMLData {
	
	private final String RPS = "RockPaperScissors";
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
	 * Returns RPS XML data fields 
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

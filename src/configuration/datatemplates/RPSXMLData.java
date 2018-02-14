package configuration.datatemplates;

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

    private final String RPS = "RPS";
    private String[] PARAMETERS;

    /**
     * Constructor
     */
    public RPSXMLData() {
	super();
	PARAMETERS = getParameters(RPS);
    }

    /**
     * Gets Ruleset object from XML parser
     */
    @Override
    public Ruleset getRules() {
	return new RPSRuleset();
    }

    /**
     * Returns grid object
     */
    @Override
    public Grid getGrid() {
	int[][] states  = getStates(4);
	return getGrid(states);
    }

    /**
     * Creates grid object from XML parser data
     */
    private Grid getGrid(int[][] states) {
	Grid g = new StandardGrid(RPS, this.getXSize(), this.getYSize());
	String gradient = myDataValues.get(PARAMETERS[0]);
	int[][] gradientStates = new CellStateGenerator().locationStates(gradient, getXSize(), getYSize());
	for(int r= 0; r < this.getXSize(); r++) {
	    for(int c = 0; c < this.getYSize(); c++) {
		g.addCell(new RPSCell(r, c, states[r][c], gradientStates[r][c]));
	    }
	}
	return g;
    }
}

package configuration.datatemplates;
import java.util.Arrays;
import java.util.List;

import simulation.cell.*;
import simulation.grid.*;
import simulation.ruleSet.*;

/**
 * Configures Game of Life XML data from parser
 * 
 * @author Katherine Van Dyk
 *
 */
public class GameOfLifeXMLData extends XMLData {
	private String GAMEOFLIFE = "Game of Life";
	protected static final List<String> PARAM_DATA_FIELDS = Arrays.asList(new String[] {
			"minLife",
			"maxLife",
			"birth"
	});

	/** 
	 * Constructor
	 */
	public GameOfLifeXMLData() {
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
	 * Gets Ruleset object from XML parser
	 */
	@Override
	public GameOfLifeRuleset getRules() {
		int minLife = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(0)));
		int maxLife = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(1)));
		int birth = Integer.parseInt(myDataValues.get(PARAM_DATA_FIELDS.get(2)));
		return new GameOfLifeRuleset(minLife, maxLife, birth);
	}

	/**
	 * Gets Grid object from XML parser
	 */
	public Grid getGrid(int[][] states) {
		Grid g = new StandardGrid(this.getXSize(), this.getYSize());
		g.setType(GAMEOFLIFE);
		for(int r= 0; r < this.getXSize(); r++) {
			for(int c = 0; c < this.getYSize(); c++) {
				g.addCell(new GameOfLifeCell(r, c, states[r][c]));
			}
		}
		return g;
	}
	
}

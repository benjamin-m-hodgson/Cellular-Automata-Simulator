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
	protected static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
			"type",
			"name",
			"sizeX",
			"sizeY",
			"cell",
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
	public List<String> getDataField() {
		return DATA_FIELDS;
	}
	
	/**
	 * Gets Ruleset object from XML parser
	 */
	@Override
	public GameOfLifeRuleset getRules() {
		int minLife = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(5)));
		int maxLife = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(6)));
		int birth = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(7)));
		return new GameOfLifeRuleset(minLife, maxLife, birth);
	}

	/**
	 * Gets Grid object from XML parser
	 */
	public Grid getGrid(int[][] states) {
		Grid g = new StandardGrid(this.getXSize(), this.getYSize());
		for(int r= 0; r < this.getXSize(); r++) {
			for(int c = 0; c < this.getYSize(); c++) {
				g.addCell(new GameOfLifeCell(r, c, states[r][c]));
			}
		}
		return g;
	}
	
}

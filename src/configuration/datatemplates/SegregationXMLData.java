package configuration.datatemplates;
import java.util.Arrays;
import java.util.List;

import simulation.cell.*;
import simulation.grid.Grid;
import simulation.grid.StandardGrid;
import simulation.ruleSet.*;

/**
 * Configures Segregation XML data from parser
 * 
 * @author Katherine Van Dyk
 *
 */
public class SegregationXMLData extends XMLData {
	protected static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
			"type",
			"name",
			"sizeX",
			"sizeY",
			"cell",
			"tolerance"
	});

	/**
	 * Constructor
	 */
	public SegregationXMLData() {
		super();
	}
	
	/**
	 * Gets data fields for segregation data
	 */
	@Override
	public List<String> getDataField() {
		return DATA_FIELDS;
	}
	
	/**
	 * Gets Ruleset object from XML parser
	 */
	@Override
	public SegregationRuleset getRules() {
		//System.out.println(myDataValues.get(DATA_FIELDS.get(4)));
		return new SegregationRuleset(Double.parseDouble(myDataValues.get(DATA_FIELDS.get(5))));
	}
	
	/**
	 * Creates grid object from XML parser data
	 */
	@Override
	public Grid getGrid(int[][] states) {
		Grid g = new StandardGrid(this.getXSize(), this.getYSize());
		for(int r= 0; r < this.getXSize(); r++) {
			for(int c = 0; c < this.getYSize(); c++) {
				g.addCell(new SegregationCell(r, c, states[r][c]));
			}
		}
		return g;
	}

}

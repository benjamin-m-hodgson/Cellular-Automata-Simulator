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
	private String SEGREGATION = "Segregation";
	protected static final List<String> PARAM_DATA_FIELDS = Arrays.asList(new String[] {
			"tolerance"
	});

	/**
	 * Constructor
	 */
	public SegregationXMLData() {
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
	public SegregationRuleset getRules() {
		//System.out.println(myDataValues.get(DATA_FIELDS.get(4)));
		return new SegregationRuleset(Double.parseDouble(myDataValues.get(PARAM_DATA_FIELDS.get(0))));
	}
	
	/**
	 * Creates grid object from XML parser data
	 */
	@Override
	public Grid getGrid(int[][] states) {
		Grid g = new StandardGrid(this.getXSize(), this.getYSize());
		g.setType(SEGREGATION);
		for(int r= 0; r < this.getXSize(); r++) {
			for(int c = 0; c < this.getYSize(); c++) {
				g.addCell(new SegregationCell(r, c, states[r][c]));
			}
		}
		return g;
	}

}

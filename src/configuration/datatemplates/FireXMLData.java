package configuration.datatemplates;
import java.util.Arrays;
import java.util.List;

import simulation.cell.*;
import simulation.grid.*;
import simulation.ruleSet.*;

/**
 * Configures Fire XML data from parser
 * 
 * @author Katherine Van Dyk
 *
 */
public class FireXMLData extends XMLData {
	protected static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
			"type",
			"name",
			"sizeX",
			"sizeY",
			"cell",
			"probCatch"
	});

	/**
	 * Constructor
	 */
	public FireXMLData() {
		super();
	}
	
	/**
	 * Returns Fire XML data fields 
	 */
	@Override
	public List<String> getDataField() {
		return DATA_FIELDS;
	}
	
	/**
	 * Gets rule parameters from XML parser
	 */
	@Override
	public FireRuleset getRules() {
		return new FireRuleset(Double.parseDouble(myDataValues.get(DATA_FIELDS.get(5))));
	}
	
	/**
	 * Gets grid object from XML parser
	 */
	public Grid getGrid(int[][] states) {
		Grid g = new StandardGrid(this.getXSize(), this.getYSize());
		for(int r= 0; r < this.getXSize(); r++) {
			for(int c = 0; c < this.getYSize(); c++) {
				g.addCell(new FireCell(r, c, states[r][c]));
			}
		}
		return g;
	}
	
}

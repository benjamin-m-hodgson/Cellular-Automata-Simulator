package configuration.datatemplates;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import simulation.grid.Grid;
import simulation.ruleSet.Ruleset;

public abstract class XMLData {

	public static final String DATA_TYPE = "simulation";
	protected Map<String, String> myDataValues;
	private static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
			"type",
			"name",
			"sizeX",
			"sizeY",
			"cell"
	});

	/**
	 * XML Data constructor
	 * 
	 * @param dataValues
	 */
	public XMLData () {
		myDataValues = null;
	}
	
	public void setMap(Map<String, String> dataValues) {
		myDataValues = dataValues;
	}
	
	public String getType() {
		return myDataValues.get(DATA_FIELDS.get(0)); 
	}

	/**
	 * Source: https://stackoverflow.com/questions/2608665/how-can-i-trim-beginning-and-ending-double-quotes-from-a-string
	 * @return
	 */
	public String getName() {
		return myDataValues.get(DATA_FIELDS.get(1)).replaceAll("^\"|\"$", "");
	}
	
	public int getXSize() {
		return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(2))); 
	}

	public int getYSize() {
		return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(3))); 
	}
	
	public Grid getGrid() {
		return null;
	}

	public Ruleset getRules() {
		return null;
	}
	
	public List<String> getDataField() {
		return DATA_FIELDS;
	}

}

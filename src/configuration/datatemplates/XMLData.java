package configuration.datatemplates;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import simulation.grid.Grid;
import simulation.ruleSet.Ruleset;

/**
 * Abstract class for XML data
 * 
 * @author Katherine Van Dyk
 *
 */
public abstract class XMLData {

	public static final String DATA_TYPE = "simulation";
	protected Map<String, String> myDataValues;
	private static final List<String> STD_DATA_FIELDS = Arrays.asList(new String[] {
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

	/**
	 * Sets map from returned from parser
	 * 
	 * @param dataValues
	 */
	public void setMap(Map<String, String> dataValues) {
		myDataValues = dataValues;
	}

	/**
	 * Sets map from returned from parser
	 * 
	 * @param dataValues
	 */
	public int[][] getStates() {			
		String[] rows = myDataValues.get(STD_DATA_FIELDS.get(3)).split("\\W+");
		int i = 0;
		int[][] ints = new int[getXSize()][getYSize()];
		for(int r= 0; r < ints.length; r++) {
			for(int c = 0; c < this.getYSize(); c++) {
				ints[r][c] = Integer.parseInt(rows[i]);
				i++;
			}
		}
		return ints;
	}

	/**
	 * Returns name of simulation
	 * Source: https://stackoverflow.com/questions/2608665/how-can-i-trim-beginning-and-ending-double-quotes-from-a-string
	 * 
	 * @return String representing unique name of simulation
	 */
	public String getName() {
		return myDataValues.get(STD_DATA_FIELDS.get(0)).replaceAll("^\"|\"$", "");
	}

	/**
	 * 
	 * @return X size of Grid
	 */
	public int getXSize() {
		return Integer.parseInt(myDataValues.get(STD_DATA_FIELDS.get(1))); 
	}

	/**
	 * 
	 * @return Y size of grid
	 */
	public int getYSize() {
		return Integer.parseInt(myDataValues.get(STD_DATA_FIELDS.get(2))); 
	}

	/**
	 * 
	 * @return Grid object (used in subclasses)
	 */
	public Grid getGrid(int[][] states) {
		return null;
	}

	/**
	 * 
	 * @return Ruleset object (used in subclasses)
	 */
	public Ruleset getRules() {
		return null;
	}

	/**
	 * 
	 * @return Gets list of all datafields in XML file
	 */
	public List<String> getStandardFields() {
		List<String> retFields = new ArrayList<String>(STD_DATA_FIELDS);
		return retFields;
	}
	
	/**
	 * 
	 * @return Gets list of all datafields in XML file
	 */
	public abstract List<String> getDataFields();
	
	/**
	 * Get the parameters specific to each simulation
	 * 
	 * @return
	 */
	public abstract List<String> getParameterFields();

}

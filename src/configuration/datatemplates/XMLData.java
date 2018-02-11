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
     * Parses input XML file for cell states, a double array of integers
     * 
     * @return int[][]: double array of integers representing initial states of all cells
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
     * Returns x-size of grid
     * 
     * @return int: x-size of Grid
     */
    public int getXSize() {
	return Integer.parseInt(myDataValues.get(STD_DATA_FIELDS.get(1))); 
    }

    /**
     * Return y-size of grid
     * 
     * @return int: y-size of grid
     */
    public int getYSize() {
	return Integer.parseInt(myDataValues.get(STD_DATA_FIELDS.get(2))); 
    }

    /**
     * Returns grid object specified by XML File
     * 
     * @return Grid object (used in subclasses)
     */
    public abstract Grid getGrid(int[][] states);
    
    /**
     * Returns ruleset object specified by XML file
     * 
     * @return Ruleset object
     */
    public abstract Ruleset getRules();

    /**
     * Gets list of all standard  datafields in XML file
     * 
     * @return List of strings detailing data fields seen in all XML files
     */
    public List<String> getStandardFields() {
	List<String> retFields = new ArrayList<String>(STD_DATA_FIELDS);
	return retFields;
    }

    /**
     * Returns all standard/simulation-specific data fields
     * 
     * @return List of all data fields 
     */
    public abstract List<String> getDataFields();

    /**
     * Returns simulation-specific parameter fields
     * 
     * @return List of simulation-specific data fields 
     */
    public abstract List<String> getParameterFields();

}

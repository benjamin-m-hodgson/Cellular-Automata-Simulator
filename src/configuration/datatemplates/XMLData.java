package configuration.datatemplates;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import configuration.XMLParsing.CellStateGenerator;
import simulation.grid.Grid;
import simulation.ruleSet.Ruleset;

/**
 * Abstract class for XML data templates. This class parses standard XML data (data consistent across XML files of all 
 * configurations) and creates a grid object and initializes a rule set based on the input parameters.
 * 
 * @author Katherine Van Dyk
 * @date 1/30/18
 *
 */
public abstract class XMLData {

    public static final String DATA_TYPE = "simulation";
    public static final String LOCATIONS = "locations";
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
     * Sets map of datavalues (string and tag name) returned from the XML parser. 
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
    public int[][] getStates(String parserType, String simulationType) {
	CellStateGenerator cellGen = new CellStateGenerator();
	if(parserType.equals(LOCATIONS)) {
		return cellGen.locationStates(myDataValues.get(STD_DATA_FIELDS.get(3)), getXSize(), getYSize());
	}
	else {
	    return cellGen.randomStates(simulationType, getXSize(), getYSize());
	}
    }

    /**
     * Returns name of simulation
     * Source Code to Parse Simulation Name:
     * https://stackoverflow.com/questions/2608665/how-can-i-trim-beginning-and-ending-double-quotes-from-a-string
     * 
     * @return String representing unique name of simulation
     */
    public String getName() {
	return myDataValues.get(STD_DATA_FIELDS.get(0)).replaceAll("^\"|\"$", "");
    }

    /**
     * Returns x-size of the grid object
     * 
     * @return int: x-size of Grid
     */
    public int getXSize() {
	return Integer.parseInt(myDataValues.get(STD_DATA_FIELDS.get(1))); 
    }

    /**
     * Return y-size of the grid object
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
     * Gets list of all standard datafields in XML file
     * 
     * @return List<String> of all parameters
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

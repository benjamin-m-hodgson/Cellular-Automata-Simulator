package configuration.datatemplates;
import java.util.Map;
import java.util.ResourceBundle;

import configuration.XMLParsing.CellStateGenerator;
import simulation.grid.Grid;
import simulation.ruleSet.Ruleset;

/**
 * XMLData serves as the superclass for all XML data templates, which are simulation-specific. The XML Data Template is 
 * called by the parser to organize the input XML file information into a map of tags and values (both strings). Since the XML
 * data template is needed beyond the configuration package, I chose to place the parameter fields within a properties file. This way,
 * when adding a new simulation, the user only has to input parameter information once/XML data templates do not have to be instantiated
 * beyond the configuration package. Subclasses of the XML Data Template create grid and rule set objects, and deal with handling
 * simulation-specific parameters (the XMLData template parses STANDARD parameter data, common to all simulations).
 * 
 * @author Katherine Van Dyk
 * @date 1/30/18
 */
public abstract class XMLData {
    private String STANDARD = "Standard";
    private String RANDOM = "random";
    private String[] STANDARD_FIELDS;
    protected ResourceBundle DEFAULT_RESOURCES = ResourceBundle.getBundle("configuration.parameters");
    protected Map<String, String> myDataValues;

    /**
     * XML Data Template constructor, initializes myDataValues (the map of XML parsed data) to be null and accesses
     * STANDARD fields within the resources file
     */
    public XMLData () {
	STANDARD_FIELDS = getParameters(STANDARD);
	myDataValues = null;
    }

    /**
     * Sets map of data values (string and tag name) returned from the XML parser. 
     * @param dataValues: Map of XML tag to value contained within that tag
     */
    public void setMap(Map<String, String> dataValues) {
	myDataValues = dataValues;
    }

    /**
     * Parses the field 'cell' field of any XML file and returns a double integer representing states
     * of the cell. This array is filled either with hard-coded states within the XML file, or randomly
     * if specified in the XML file.
     * @return int[][]: double array of integers representing initial states of all cells
     */
    protected int[][] getStates(int maxStates) {
	CellStateGenerator cellGen = new CellStateGenerator();
	if(!myDataValues.get(STANDARD_FIELDS[3]).equals(RANDOM)) {
	    return cellGen.locationStates(myDataValues.get(STANDARD_FIELDS[3]), getXSize(), getYSize());
	}
	else {
	    return cellGen.randomStates(maxStates, getXSize(), getYSize());
	}
    }

    /**
     * Returns the unique name of a simulation
     * @return String representing unique name of simulation
     */
    public String getName() {
	return myDataValues.get(STANDARD_FIELDS[0]);
    }

    /**
     * Returns x-size of the grid object
     * @return int: x-size of Grid
     */
    public int getXSize() {
	return Integer.parseInt(myDataValues.get(STANDARD_FIELDS[1])); 
    }

    /**
     * Return y-size of the grid object
     * @return int: y-size of grid
     */
    public int getYSize() {
	return Integer.parseInt(myDataValues.get(STANDARD_FIELDS[2])); 
    }

    /**
     * @return Grid: Grid object using simulation-specific cell type
     */
    public abstract Grid getGrid();

    /**
     * @return Ruleset: Ruleset object specific to simulation/input parameters in XML File
     */
    public abstract Ruleset getRules();

    /**
     * Returns array of space separated strings in the XML file
     * @param key: desired string title
     * @return String[]: corresponding resource string array
     */
    public String[] getParameters(String simType) {
	return DEFAULT_RESOURCES.getString(simType).split(" ");
    }
}

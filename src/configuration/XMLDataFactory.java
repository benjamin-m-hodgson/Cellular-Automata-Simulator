package configuration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import configuration.datatemplates.*;

/**
 * This factory class chooses the correct data template type based on the simulation
 * and also hold the resource strings containing all simulation types, accessed by all
 * XML parsing/writing classes
 * 
 * @author Katherine Van Dyk
 *
 */
public class XMLDataFactory {
    private final ResourceBundle SIMULATION_RESOURCES = 
	    ResourceBundle.getBundle("configuration.parameters");
    protected Map<String, XMLData> DATA_TEMPLATES = new HashMap<>();

    /**
     * Chooses XML Data template based on simulation type
     * 
     * @param simType
     * @return
     */
    public List<String> getDataFields(String simType) {
	ArrayList<String> retlist = new ArrayList<>();
	retlist.addAll(getParameters("Standard"));
	retlist.addAll(getParameters(simType));
	return retlist;
    }
    
    /**
     * Returns simulation name from resources file
     * 
     * @param key
     * @return
     */
    public List<String> getParameters(String key) {
	String[] parameters = SIMULATION_RESOURCES.getString(key).split(" ");
	return Arrays.asList(parameters);
    }

    /**
     * Chooses XML Data template based on simulation type
     * 
     * @param simType: Type of simulation
     * @return XMLData template used for parameter-specific parsing
     */
    public XMLData chooseDataTemplate(String simType) {
	constructMap();
	return DATA_TEMPLATES.get(simType);
    }

    /**
     * Returns simulation name from resources file
     * 
     * @param key
     * @return
     */
    protected String getSimulation(String key) {
	return SIMULATION_RESOURCES.getString(key);
    }
    
    /**
     * Maps each simulation name to its data template
     */
    private void constructMap() {
	DATA_TEMPLATES.put("Fire", new FireXMLData());
	DATA_TEMPLATES.put("WaTor", new WaTorXMLData());
	DATA_TEMPLATES.put("Segregation", new SegregationXMLData());
	DATA_TEMPLATES.put("GameOfLife", new GameOfLifeXMLData());
	DATA_TEMPLATES.put("RPS", new RPSXMLData());
	DATA_TEMPLATES.put("SugarScape", new SugarXMLData());
    }
}

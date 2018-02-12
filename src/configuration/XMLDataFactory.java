package configuration;
import java.util.List;
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
	    ResourceBundle.getBundle("simulation.default");

    /**
     * Chooses XML Data template based on simulation type
     * 
     * @param simType
     * @return
     */
    public List<String> getDataFields(String simType) {
	XMLData dataTemplate = chooseDataTemplate(simType);
	return dataTemplate.getDataFields();
    }

    /**
     * Chooses XML Data template based on simulation type
     * 
     * @param simType
     * @return
     */
    public List<String> getParameters(String simType) {
	XMLData dataTemplate = chooseDataTemplate(simType);
	return dataTemplate.getParameterFields();
    }

    /**
     * Chooses XML Data template based on simulation type
     * 
     * @param simType: Type of simulation
     * @return XMLData template used for parameter-specific parsing
     */
    public XMLData chooseDataTemplate(String simType) {
	if(simType.equals(getSimulation("FIRE"))){
	    return new FireXMLData();
	}
	else if(simType.equals(getSimulation("WATOR"))) {
	    return new WaTorXMLData();
	}
	else if(simType.equals(getSimulation("GAMEOFLIFE"))) {
	    return new GameOfLifeXMLData();
	}
	else if(simType.equals(getSimulation("SEGREGATION"))) {
	    return new SegregationXMLData();
	}
	else if(simType.equals(getSimulation("ROCKPAPERSCISSORS"))) {
	    return new RPSXMLData();
	}
	else return null;
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
}

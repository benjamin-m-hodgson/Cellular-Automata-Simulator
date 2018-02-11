package factoryClasses;
import java.util.List;

import configuration.datatemplates.*;
import simulation.Engine;

public class StyleFactory extends Engine {
    private static final String FIRE = "Fire";
    private static final String WATOR = "WaTor";
    private static final String SEGREGATION = "Segregation";
    private static final String GAMEOFLIFE = "Game of Life";

    public List<String> getParameters(String simType){
	if(simType.equals(FIRE)) {
	    FireXMLData fire = new FireXMLData();
	    return fire.getParameterFields();
	}
	else if(simType.equals(WATOR)) {
	    WaTorXMLData wator = new WaTorXMLData();
	    return wator.getParameterFields();
	}
	else if(simType.equals(SEGREGATION)) {
	    SegregationXMLData segregation = new SegregationXMLData();
	    return segregation.getParameterFields();
	}
	else if(simType.equals(GAMEOFLIFE)) {
	    FireXMLData gol = new FireXMLData();
	    return gol.getParameterFields();
	}
	return null;
    }

}

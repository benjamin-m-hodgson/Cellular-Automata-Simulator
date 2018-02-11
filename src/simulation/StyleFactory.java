package simulation;
import java.util.List;

import configuration.datatemplates.*;

public class StyleFactory extends Engine {
    private static final String FIRE = "Fire";
    private static final String WATOR = "WaTor";
    private static final String SEGREGATION = "Segregation";
    private static final String GAMEOFLIFE = "Game of Life";

    public double currentShapeHeight() {
	return SHAPE_HEIGHT;
    }

    public double currentShapeWidth() {
	return SHAPE_WIDTH;
    }

    public double currentShapeSpace() {
	return SHAPE_SPACE;
    }

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

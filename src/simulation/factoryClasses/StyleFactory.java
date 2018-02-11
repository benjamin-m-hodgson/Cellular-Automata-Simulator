package simulation.factoryClasses;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import configuration.datatemplates.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import simulation.Engine;
import simulation.ruleSet.FireRuleset;
import simulation.ruleSet.GameOfLifeRuleset;
import simulation.ruleSet.SegregationRuleset;
import simulation.ruleSet.WaTorRuleset;

/**
 * Handles grid styling choices based on simulation type and user input
 * (grid edges and shape style)
 * 
 * @author Katherine Van Dyk
 */
public class StyleFactory extends Engine {

    /**
     * @return simulation-specific parameter fields for drop-down menus and 
     * XML file writing
     * @param simType: Simulation type
     */
    public List<String> getParameters(String simType){
	if(simType.equals(resourceString("FIRE"))) {
	    FireXMLData fire = new FireXMLData();
	    return fire.getParameterFields();
	}
	else if(simType.equals(resourceString("WATOR"))) {
	    WaTorXMLData wator = new WaTorXMLData();
	    return wator.getParameterFields();
	}
	else if(simType.equals(resourceString("SEGREGATION"))) {
	    SegregationXMLData segregation = new SegregationXMLData();
	    return segregation.getParameterFields();
	}
	else if(simType.equals(resourceString("GAMEOFLIFE"))) {
	    FireXMLData gol = new FireXMLData();
	    return gol.getParameterFields();
	}
	return null;
    }

    /**
     * Returns list of ways for grid edges to be handled
     */
    public ObservableList<String> getEdgeHandling(){
	List<String> EDGE_HANDLING_FIELDS = Arrays.asList(new String[] {
		"finite",
		"toroidal"
	});
	ArrayList<String> options = new ArrayList<>();
	options.addAll(EDGE_HANDLING_FIELDS);
	ObservableList<String> retList = FXCollections.observableArrayList(options);
	return retList;
    }

    /**
     * Returns list of different shapes
     */
    public ObservableList<String> getShapes(){
	List<String> SHAPE_FIELDS = Arrays.asList(new String[] {
		"rectangle",
		"triangle"
	});
	ArrayList<String> options = new ArrayList<>();
	options.addAll(SHAPE_FIELDS);
	ObservableList<String> retList = FXCollections.observableArrayList(options);
	return retList;
    }

    /**
     * Sets parameters of ruleset to values based on user input
     * 
     * @param engine: Current instance of game engine
     * @param param: All possible game parameters
     * @param newVal: Updated values
     */
    public void setParameter(Engine engine, String param, double newVal){
	String simType = engine.currentGrid().getType();
	if(simType.equals(resourceString("FIRE"))) {
	    handleFireChange((FireRuleset) engine.currentRules(), newVal);
	}
	else if(simType.equals(resourceString("WATOR"))) {
	    List<String> parameters = getParameters(simType);
	    handleWaTorChange((WaTorRuleset) engine.currentRules(), newVal, param, parameters);
	}
	else if(simType.equals(resourceString("SEGREGATION"))) {
	    handleSegregationChange((SegregationRuleset) engine.currentRules(), newVal);
	}
	else if(simType.equals(resourceString("GAMEOFLIFE"))) {
	    List<String> parameters = getParameters(simType);
	    handleGameOfLifeChange((GameOfLifeRuleset) engine.currentRules(), newVal, param, parameters);
	}
    }

    /**
     * Changes value in current FireRuleset @param r to new value @param newVal
     */
    private void handleFireChange(FireRuleset r, double newVal) {
	r.setProbCatch(newVal);
    }

    /**
     * Changes value in current WaTorRuleset @param r to new value @param newVal
     */
    private void handleWaTorChange(WaTorRuleset r, double newVal, String param, List<String> parameters) {
	if(param.equals(parameters.get(0))) {
	    r.setFishBreedTime((int) newVal);
	}
	else if(param.equals(parameters.get(1))) {
	    r.setFishInitEnergy((int) newVal);
	}
	else if(param.equals(parameters.get(2))) {
	    r.setSharkInitEnergy((int) newVal);
	}
	else if(param.equals(parameters.get(3))){
	    r.setSharkBreedEnergy((int) newVal);
	}		
    }

    /**
     * Changes value in current SegregationRuleset @param r to new value @param newVal
     */
    private void handleSegregationChange(SegregationRuleset r,  double newVal) {
	r.setTolerance(newVal);
    }

    /**
     * Changes value in current GameoOfLifeRuleset @param r to new value @param newVal
     */
    private void handleGameOfLifeChange(GameOfLifeRuleset r, double newVal, String param, List<String> parameters) {
	if(param.equals(parameters.get(0))) {
	    r.setMinLife((int) newVal);
	}
	else if(param.equals(parameters.get(2))) {
	    r.setMaxLife((int) newVal);
	}
	else if(param.equals(parameters.get(3))){
	    r.setBirth((int) newVal);
	}	
    }
}

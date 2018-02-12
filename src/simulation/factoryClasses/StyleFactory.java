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
import simulation.ruleSet.SugarRuleset;
import simulation.ruleSet.WaTorRuleset;

/**
 * Handles grid/ruleset styling choices displayed in settings panel and edited by
 * user input.
 * 
 * @author Katherine Van Dyk
 * @date 2/10/18
 */
public class StyleFactory extends Engine {
    private static final double DEFAULT_VALUE = 0.0;
    private static final String FIRE = "Fire";
    private static final String WATOR = "WaTor";
    private static final String SEGREGATION = "Segregation";
    private static final String GAMEOFLIFE = "Game of Life";
    private static final String SUGAR = "SugarScape";
    private static final String RPS = "Rock, Paper, Scissors";

    /**
     * Returns list of parameters for specific simulation type
     * 
     * @param simType: current simulation in Engine
     * @return List<String> to be displayed in drop-down menus
     */
    public List<String> getParameters(String simType){
	if(simType.equalsIgnoreCase(FIRE)) {
	    FireXMLData fire = new FireXMLData();
	    return fire.getParameterFields();
	}
	else if(simType.equalsIgnoreCase(WATOR)) {
	    WaTorXMLData wator = new WaTorXMLData();
	    return wator.getParameterFields();
	}
	else if(simType.equalsIgnoreCase(SEGREGATION)) {
	    SegregationXMLData segregation = new SegregationXMLData();
	    return segregation.getParameterFields();
	}
	else if(simType.equalsIgnoreCase(GAMEOFLIFE)) {
	    GameOfLifeXMLData gol = new GameOfLifeXMLData();
	    return gol.getParameterFields();
	}
	else if(simType.equalsIgnoreCase(SUGAR)) {
	    SugarXMLData sugar = new SugarXMLData();
	    List<String> completeList = sugar.getParameterFields();
	    // only the first two parameters are global parameters
	    List<String> globalParameters = completeList.subList(0, 2);
	    return globalParameters;
	}
	else if(simType.equalsIgnoreCase(RPS)) {
	    // RPS has no global variables to change
	    return new ArrayList<String>();
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
     * 
     * @param engine
     * @param param
     * @param newVal
     */
    public void setParameter(Engine engine, String param, double newVal){
	String simType = engine.currentGrid().getType();
	if(simType.equals(FIRE)) {
	    FireRuleset fr = ((FireRuleset) engine.currentRules());
	    fr.setProbCatch(newVal);
	}
	else if(simType.equals(WATOR)) {
	    WaTorRuleset wr = ((WaTorRuleset) engine.currentRules());
	    List<String> parameters = getParameters(simType);
	    if(param.equals(parameters.get(0))) {
		wr.setFishBreedTime((int) newVal);
	    }
	    else if(param.equals(parameters.get(1))) {
		wr.setFishInitEnergy((int) newVal);
	    }
	    else if(param.equals(parameters.get(2))) {
		wr.setSharkInitEnergy((int) newVal);
	    }
	    else if(param.equals(parameters.get(3))){
		wr.setSharkBreedEnergy((int) newVal);
	    }		
	}
	else if(simType.equals(SEGREGATION)) {
	    SegregationRuleset sr = ((SegregationRuleset) engine.currentRules());
	    sr.setTolerance(newVal);
	}
	else if(simType.equals(GAMEOFLIFE)) {
	    GameOfLifeRuleset gr = ((GameOfLifeRuleset) engine.currentRules());
	    List<String> parameters = getParameters(simType);
	    if(param.equals(parameters.get(0))) {
		gr.setMinLife((int) newVal);
	    }
	    else if(param.equals(parameters.get(2))) {
		gr.setMaxLife((int) newVal);
	    }
	    else if(param.equals(parameters.get(3))){
		gr.setBirth((int) newVal);
	    }		
	}
	else if(simType.equalsIgnoreCase(SUGAR)) {
	    SugarRuleset sugar = ((SugarRuleset) engine.currentRules());
	    List<String> parameters = getParameters(simType);
	    if(param.equals(parameters.get(0))) {
		sugar.setRegenRate((int) newVal); 
	    }
	    else if(param.equals(parameters.get(1))) {
		sugar.setRegenInterval((int) newVal);
	    }
	}
    }

    /**
     * Gets specific parameter value from current simulation
     * 
     * @param engine
     * @param param
     * @return
     */
    public double getParameter(Engine engine, String param){
	String simType = engine.currentGrid().getType();
	if(simType.equals(FIRE)) {
	    FireRuleset fr = ((FireRuleset) engine.currentRules());
	    return fr.getProbCatch();
	}
	else if(simType.equals(WATOR)) {
	    WaTorRuleset wr = ((WaTorRuleset) engine.currentRules());
	    List<String> parameters = getParameters(simType);
	    if(param.equals(parameters.get(0))) {
		return wr.getFishBreedTime();
	    }
	    else if(param.equals(parameters.get(1))) {
		return wr.getFishInitEnergy();
	    }
	    else if(param.equals(parameters.get(2))) {
		return wr.getSharkInitEnergy();
	    }
	    else if(param.equals(parameters.get(3))) {
		return wr.getSharkBreedEnergy();
	    }		
	}
	else if(simType.equals(SEGREGATION)) {
	    SegregationRuleset sr = ((SegregationRuleset) engine.currentRules());
	    return sr.getTolerance();
	}
	else if(simType.equals(GAMEOFLIFE)) {
	    GameOfLifeRuleset gr = ((GameOfLifeRuleset) engine.currentRules());
	    List<String> parameters = getParameters(simType);
	    if(param.equals(parameters.get(0))) {
		return gr.getMinLife();
	    }
	    else if(param.equals(parameters.get(2))) {
		return gr.getMaxLife();
	    }
	    else if(param.equals(parameters.get(3))) {
		return gr.getBirth();
	    }		
	}
	else if(simType.equals(SUGAR)) {
	    SugarRuleset sugar = ((SugarRuleset) engine.currentRules());
	    List<String> parameters = getParameters(simType);
	    if(param.equals(parameters.get(0))) {
		return sugar.getRegenRate();
	    }
	    else if(param.equals(parameters.get(1))) {
		return sugar.getRegenInterval();
	    }
	}
	return DEFAULT_VALUE;
    }
}

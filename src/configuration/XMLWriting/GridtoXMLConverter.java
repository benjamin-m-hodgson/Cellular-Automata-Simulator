package configuration.XMLWriting;

import java.util.ArrayList;
import java.util.List;

import configuration.XMLDataFactory;
import simulation.grid.Grid;
import simulation.ruleSet.FireRuleset;
import simulation.ruleSet.GameOfLifeRuleset;
import simulation.ruleSet.Ruleset;
import simulation.ruleSet.SegregationRuleset;
import simulation.ruleSet.WaTorRuleset;

/**
 * Use this class as a helper class for XML writer, to take in grid and ruleset objects and create
 * a list of strings of parameters in an XML format (to insert into the XML file), as well as a list of
 * current cell states. 
 * 
 * @author Katherine Van Dyk
 * @date 2/8/18
 *
 */
public class GridtoXMLConverter extends XMLDataFactory{

    /**
     * Writes specific ruleset parameters into strings to be placed into the XML file
     * 
     * @param simType: Simulation type being written
     * @param r: ruleset being written into XML file
     * @param rootElement: 
     * @return
     */
    public List<String> rulesetParam(String simType, Ruleset rules) {
	List<String> res = new ArrayList<String>();
	if(simType.equals(getSimulation("FIRE")))	{
	    FireRuleset fr = (FireRuleset) rules;
	    res.add(Double.toString(fr.getProbCatch()));
	    return res;
	}
	else if(simType.equals(getSimulation("WATOR"))) {
	    WaTorRuleset wr = (WaTorRuleset) rules;
	    res.add(Integer.toString(wr.getFishBreedTime()));
	    res.add(Integer.toString(wr.getFishInitEnergy()));
	    res.add(Integer.toString(wr.getSharkInitEnergy()));
	    res.add(Integer.toString(wr.getSharkBreedEnergy()));
	    return res;
	}
	else if(simType.equals(getSimulation("GAMEOFLIFE"))) {
	    GameOfLifeRuleset gr = (GameOfLifeRuleset) rules;
	    res.add(Integer.toString(gr.getMinLife()));
	    res.add(Integer.toString(gr.getMaxLife()));
	    res.add(Integer.toString(gr.getBirth()));
	    return res;
	}
	else if(simType.equals(getSimulation("SEGREGATION"))) {
	    SegregationRuleset sr = (SegregationRuleset) rules;
	    res.add(Double.toString(sr.getTolerance()));
	    return res;
	}
	else return null;
    }
    
    /**
     * Takes in grid object and returns a string of cells states to be placed into the XML file
     * 
     * @param g
     * @return
     */
    public String cellStates(Grid g) {
	StringBuilder result = new StringBuilder();
	StringBuilder row = new StringBuilder();
	for(int r = 0; r < g.getXSize(); r++) {
	    row.setLength(0);
	    for(int c = 0; c < g.getYSize(); c++) {
		row.append(Integer.toString(g.getCell(r, c).getState()) + " ");
	    }
	    result.append(row.toString() + "\n");
	}
	return result.toString();
    }
    

}

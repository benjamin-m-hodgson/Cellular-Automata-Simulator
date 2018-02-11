package configuration.XMLParsing;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.w3c.dom.Element;
import simulation.ruleSet.Ruleset;
import configuration.datatemplates.FireXMLData;
import configuration.datatemplates.GameOfLifeXMLData;
import configuration.datatemplates.SegregationXMLData;
import configuration.datatemplates.WaTorXMLData;
import configuration.datatemplates.XMLData;
import simulation.ruleSet.*;

/**
 * Chooses correct fields for a simulation based on its type
 * 
 * @author Katherine Van Dyk
 *
 */
public class XMLDataFactory {
    private static final String FIRE = "Fire";
    private static final String WATOR = "WaTor";
    private static final String SEGREGATION = "Segregation";
    private static final String GAMEOFLIFE = "Game of Life";

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
	if(simType.equals(FIRE))	{
	    return new FireXMLData();
	}
	else if(simType.equals(WATOR)) {
	    return new WaTorXMLData();
	}
	else if(simType.equals(GAMEOFLIFE)) {
	    return new GameOfLifeXMLData();
	}
	else if(simType.equals(SEGREGATION)) {
	    return new SegregationXMLData();
	}
	else return null;
    }


    /**
     * Chooses cell state randomly
     * 
     * @param simType
     * @param xSize
     * @param ySize
     * @return
     */
    protected int[][] randomStates(String simType, int xSize, int ySize) {
	Random rand = new Random();
	int[][] states = new int[xSize][ySize];
	if(simType.equals(FIRE) || simType.equals(WATOR) || simType.equals(SEGREGATION)) {
	    for (int r = 0; r < xSize; r++) {
		for(int c = 0; c < ySize; c++) {
		    states[r][c] = rand.nextInt(3);
		}
	    }
	}
	else {
	    for (int r = 0; r < xSize; r++) {
		for(int c = 0; c < ySize; c++) {
		    states[r][c] = rand.nextInt(2);
		}
	    }
	}
	return states;
    }

}

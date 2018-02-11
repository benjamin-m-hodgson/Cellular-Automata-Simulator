package configuration.XMLWriting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.w3c.dom.Element;

import simulation.grid.Grid;
import simulation.ruleSet.FireRuleset;
import simulation.ruleSet.GameOfLifeRuleset;
import simulation.ruleSet.Ruleset;
import simulation.ruleSet.SegregationRuleset;
import simulation.ruleSet.WaTorRuleset;

public class GridtoXMLConverter {
    private static final String FIRE = "Fire";
    private static final String WATOR = "WaTor";
    private static final String SEGREGATION = "Segregation";
    private static final String GAMEOFLIFE = "Game of Life";

    /**
     * Writes specific ruleset parameters into strings
     * 
     * @param simType
     * @param r
     * @param rootElement
     * @return
     */
    public List<String> rulesetParam(String simType, Ruleset r, Element rootElement) {
	List<String> res = new ArrayList<String>();
	if(simType.equals(FIRE))	{
	    FireRuleset fr = (FireRuleset) r;
	    res.add(Double.toString(fr.getProbCatch()));
	    return res;
	}
	else if(simType.equals(WATOR)) {
	    WaTorRuleset wr = (WaTorRuleset) r;
	    res.add(Integer.toString(wr.getFishBreedTime()));
	    res.add(Integer.toString(wr.getFishInitEnergy()));
	    res.add(Integer.toString(wr.getSharkInitEnergy()));
	    res.add(Integer.toString(wr.getSharkBreedEnergy()));
	    return res;
	}
	else if(simType.equals(GAMEOFLIFE)) {
	    GameOfLifeRuleset gr = (GameOfLifeRuleset) r;
	    res.add(Integer.toString(gr.getMinLife()));
	    res.add(Integer.toString(gr.getMaxLife()));
	    res.add(Integer.toString(gr.getBirth()));
	    return res;
	}
	else if(simType.equals(SEGREGATION)) {
	    SegregationRuleset sr = (SegregationRuleset) r;
	    res.add(Double.toString(sr.getTolerance()));
	    return res;
	}
	else return null;
    }
    
	/**
	 * Returns string representing cell states
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

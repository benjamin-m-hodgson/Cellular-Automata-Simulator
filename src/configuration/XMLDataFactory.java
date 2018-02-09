package configuration;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import simulation.ruleSet.Ruleset;
import configuration.datatemplates.FireXMLData;
import configuration.datatemplates.GameOfLifeXMLData;
import configuration.datatemplates.SegregationXMLData;
import configuration.datatemplates.WaTorXMLData;
import configuration.datatemplates.XMLData;
import simulation.ruleSet.*;

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
		return dataTemplate.getDataField();
	}
	
	/**
	 * Chooses XML Data template based on simulation type
	 * 
	 * @param simType
	 * @return
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

	
}

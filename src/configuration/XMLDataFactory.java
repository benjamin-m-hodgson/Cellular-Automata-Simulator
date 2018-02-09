package configuration;

import java.util.List;

import configuration.datatemplates.FireXMLData;
import configuration.datatemplates.GameOfLifeXMLData;
import configuration.datatemplates.SegregationXMLData;
import configuration.datatemplates.WaTorXMLData;
import configuration.datatemplates.XMLData;

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
	private XMLData chooseDataTemplate(String simType) {
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
	
}

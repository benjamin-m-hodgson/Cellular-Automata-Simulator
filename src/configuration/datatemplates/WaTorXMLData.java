package configuration.datatemplates;

import java.util.Arrays;
import java.util.List;

import simulation.cell.*;
import simulation.grid.Grid;
import simulation.grid.StandardGrid;
import simulation.ruleSet.*;

/**
 * Configures WaTor XML data from parser
 * 
 * @author Katherine Van Dyk
 *
 */
public class WaTorXMLData extends XMLData {
	
	private int FISH = 0;
	private int SHARK = 1; 
	private int NOENERGY = 0;
	protected static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
			"type",
			"name",
			"sizeX",
			"sizeY",
			"cell",
			"fishBreedTime",
			"fishEnergy",
			"sharkInitEnergy",
			"sharkBreedEnergy",
	});

	/**
	 * Constructor
	 */
	public WaTorXMLData() {
		super();
	}

	/**
	 * Gets Ruleset object from XML parser
	 */
	@Override
	public WaTorRuleset getRules() {
		int fishBreedTime = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(5)));
		int fishInitEnergy = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(6)));
		int sharkInitEnergy = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(7)));
		int sharkBreedTime = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(8)));
		return new WaTorRuleset(fishBreedTime, sharkBreedTime, fishInitEnergy, sharkInitEnergy);
	}
	
	/**
	 * Gets data fields for WaTor simulation
	 */
	@Override
	public List<String> getDataField() {
		return DATA_FIELDS;
	}
	
	/**
	 * Creates grid object from XML parser data
	 */
	@Override
	public Grid getGrid() {
		int state;
		Grid g = new StandardGrid(this.getXSize(), this.getYSize());
		String[] ints = myDataValues.get(DATA_FIELDS.get(4)).split("\\W+");
		int sharkEnergy = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(7)));
		int fishEnergy = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(6)));
		int p=0;
		for(int r= 0; r < this.getXSize(); r++) {
			for(int c = 0; c < this.getYSize(); c++) {
				state = Integer.parseInt(ints[p]);
				if(state == FISH) {
					g.addCell(new WaTorCell(r, c, state, fishEnergy));
				}
				else if(state == SHARK) {
					g.addCell(new WaTorCell(r, c, state, sharkEnergy));
				}
				else {
					g.addCell( new WaTorCell(r, c, state, NOENERGY));
				}
				p++;
			}
		}
		return g;
	}

}

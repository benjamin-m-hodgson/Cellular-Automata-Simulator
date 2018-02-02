package configuration.datatemplates;

import java.util.Arrays;
import java.util.List;

import simulation.cell.*;
import simulation.grid.Grid;
import simulation.grid.StandardGrid;
import simulation.ruleSet.*;

public class WaTorXMLData extends XMLData {
	
	public static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
			"type",
			"name",
			"sizeX",
			"sizeY",
			"cell",
			"fishBreedTime",
			"sharkBreedTime",
			"sharkInitEnergy",
			"fishEnergy"
	});

	
	public WaTorXMLData() {
		super();
	}

	@Override
	public WaTorRuleset getRules() {
		int fishBreedTime = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(5)));
		int sharkBreedTime = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(6)));
		int sharkInitEnergy = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(7)));
		int fishEnergy = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(8)));
		return new WaTorRuleset(fishBreedTime, sharkBreedTime, sharkInitEnergy, fishEnergy);
	}
	
	@Override
	public List<String> getDataField() {
		return DATA_FIELDS;
	}
	
	@Override
	public Grid getGrid() {
		Grid g = new StandardGrid(this.getXSize(), this.getYSize());
		String[] ints = myDataValues.get(DATA_FIELDS.get(4)).split("\\W+");
		int p=0;
		for(int r= 0; r < this.getXSize(); r++) {
			for(int c = 0; c < this.getYSize(); c++) {
				g.addCell(r, c, new WaTorCell(r, c, Integer.parseInt(ints[p])));
				p++;
			}
		}
		return g;
	}

}

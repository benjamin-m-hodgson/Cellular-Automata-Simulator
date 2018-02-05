package configuration.datatemplates;

import java.util.Arrays;
import java.util.List;

import simulation.cell.*;
import simulation.grid.Grid;
import simulation.grid.StandardGrid;
import simulation.ruleSet.*;

public class SegregationXMLData extends XMLData {
	
	public static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
			"type",
			"name",
			"sizeX",
			"sizeY",
			"cell",
			"tolerance"
	});

	
	public SegregationXMLData() {
		super();
	}

	@Override
	public SegregationRuleset getRules() {
		return new SegregationRuleset(Double.parseDouble(myDataValues.get(DATA_FIELDS.get(5))));
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
				g.addCell(r, c, new SegregationCell(r, c, Integer.parseInt(ints[p])));
				p++;
			}
		}
		return g;
	}

}

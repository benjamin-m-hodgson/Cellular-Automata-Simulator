package configuration.datatemplates;
import java.util.Arrays;
import java.util.List;

import simulation.cell.*;
import simulation.grid.*;
import simulation.ruleSet.*;

public class GameOfLifeXMLData extends XMLData {
	protected static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
			"type",
			"name",
			"sizeX",
			"sizeY",
			"cell",
			"minLife",
			"maxLife",
			"birth"
	});

	
	public GameOfLifeXMLData() {
		super();
	}
	
	@Override
	public GameOfLifeRuleset getRules() {
		int minLife = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(5)));
		int maxLife = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(6)));
		int birth = Integer.parseInt(myDataValues.get(DATA_FIELDS.get(7)));
		return new GameOfLifeRuleset(minLife, maxLife, birth);
	}
	
	@Override
	public List<String> getDataField() {
		return DATA_FIELDS;
	}
	
	public Grid getGrid() {
		Grid g = new StandardGrid(this.getXSize(), this.getYSize());
		String[] ints = myDataValues.get(DATA_FIELDS.get(4)).split("\\W+");
		int p=0;
		for(int r= 0; r < this.getXSize(); r++) {
			for(int c = 0; c < this.getYSize(); c++) {
				g.addCell(new GameOfLifeCell(r, c, Integer.parseInt(ints[p])));
				p++;
			}
		}
		return g;
	}
	
}

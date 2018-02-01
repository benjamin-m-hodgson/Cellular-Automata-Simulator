package configuration.datatemplates;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import simulation.cell.Cell;
import simulation.grid.Grid;
import simulation.ruleSet.Ruleset;

public class FireXMLData extends XMLData {
	public static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
			"type",
			"name",
			"sizeX",
			"sizeY",
			"cell",
			"probCatch",
	});

	
	public FireXMLData() {
		super();
		// TODO Auto-generated constructor stub
	}


	private Cell makeCell() {
		return null;
		
	}
	
	
}

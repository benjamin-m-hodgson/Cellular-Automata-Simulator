package configuration.datatemplates;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import simulation.cell.Cell;
import simulation.grid.Grid;

public class XMLData {

	public static final String DATA_TYPE = "simulation";
	private Map<String, String> myDataValues;
	public static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
			"name",
			"sizeX",
			"sizeY",
			"cell"
	});

	/**
	 * XML Data constructor
	 * 
	 * @param dataValues
	 */
	public XMLData (Map<String, String> dataValues) {
		myDataValues = dataValues;
	}

	public String getName() {
		return myDataValues.get(DATA_FIELDS.get(0)); 
	}
	
	public int getXSize() {
		return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(1))); 
	}

	public int getYSize() {
		return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(2))); 
	}

	public Grid getGrid() {
		Grid g = new Grid(this.getXSize(), this.getYSize());
		String[] ints = myDataValues.get(DATA_FIELDS.get(3)).split("\\W+");
		int p=0;
		for(int r= 0; r < this.getXSize(); r++) {
			for(int c = 0; c < this.getYSize(); c++) {
				g.add(r, c, new Cell(Integer.parseInt(ints[p])));
				p++;
			}
		}
		return g;
	}

}

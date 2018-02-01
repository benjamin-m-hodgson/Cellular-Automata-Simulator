package DataTemplates;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class XMLData {

	public static final String DATA_TYPE = "simulation";
	private Map<String, String> myDataValues;
	public static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
			"sizeX",
			"sizeY",
			"cell",
			"x",
			"y",
			"initState"
	});

	/**
	 * XML Data constructor
	 * 
	 * @param dataValues
	 */
	public XMLData (Map<String, String> dataValues) {
		myDataValues = dataValues;
	}

	public String getXSize() {
		return myDataValues.get(DATA_FIELDS.get(0));
	}

	public String getYSize () {
		return myDataValues.get(DATA_FIELDS.get(1));
	}

	public int getCell() {
		return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(2)));
	}

	/**
	 * Converts map to string
	 */
	@Override
	public String toString () {
		StringBuilder result = new StringBuilder();
		result.append("Simulation {\n");
		for (Map.Entry<String, String> e : myDataValues.entrySet()) {
			result.append("  "+e.getKey()+"='"+e.getValue()+"',\n");
		}
		result.append("}\n");
		return result.toString();
	}

}

package configuration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class XMLData {

	public static final String DATA_TYPE = "simulation";
	public static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
			"sizeX",
			"sizeY",
			"cell",
			"x",
			"y",
			"initState"
	});

	// specific data values for this instance
	private Map<String, String> myDataValues;


	public XMLData (Map<String, String> dataValues) {
		myDataValues = dataValues;
	}

	// provide alternate ways to access data values if needed
	public String getXSize() {
		return myDataValues.get(DATA_FIELDS.get(0));
	}

	public String getYSize () {
		return myDataValues.get(DATA_FIELDS.get(1));
	}

	public int getCell() {
		return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(2)));
	}

	@Override
	public String toString () {
		StringBuilder result = new StringBuilder();
		result.append("Music {\n");
		for (Map.Entry<String, String> e : myDataValues.entrySet()) {
			result.append("  "+e.getKey()+"='"+e.getValue()+"',\n");
		}
		result.append("}\n");
		return result.toString();
	}

}

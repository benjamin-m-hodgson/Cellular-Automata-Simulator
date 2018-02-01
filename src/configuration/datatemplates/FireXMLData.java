package configuration.datatemplates;
import java.util.Arrays;
import java.util.List;
import simulation.ruleSet.FireRuleset;

public class FireXMLData extends XMLData {
	public static final List<String> DATA_FIELDS = Arrays.asList(new String[] {
			"type",
			"name",
			"sizeX",
			"sizeY",
			"cell",
			"probCatch"
	});

	
	public FireXMLData() {
		super();
	}
	

	@Override
	public FireRuleset getRules() {
		return new FireRuleset(Double.parseDouble(myDataValues.get(DATA_FIELDS.get(5))));
	}
	
	@Override
	public List<String> getDataField() {
		return DATA_FIELDS;
	}
	
	
}

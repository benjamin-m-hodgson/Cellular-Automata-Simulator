package simulation.factoryClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class ColorMapper {
    private final ResourceBundle COLOR_RESOURCES = 
	    ResourceBundle.getBundle("simulation.color");

    private HashMap<String, String[]> getPallettesMap() {
	HashMap<String, String[]> colors = new HashMap<>();
	for(String scheme : COLOR_RESOURCES.keySet()) {
	    if(!scheme.contains("Default")) colors.put(scheme, COLOR_RESOURCES.getString(scheme).split(" "));
	}
	return colors;
    }
    
    public List<String> getColorOptions() {
	ArrayList<String> retList = new ArrayList<>();
	for(String key : getPallettesMap().keySet()) {
	    retList.add(key);
	}
	return retList;
    }
    
    public String[] getColors(String color) {
	HashMap<String, String[]> colors = new HashMap<>();
	for(String scheme : COLOR_RESOURCES.keySet()) {
	    colors.put(scheme, COLOR_RESOURCES.getString(scheme).split(" "));
	}
	return colors.get(color);
    }
}

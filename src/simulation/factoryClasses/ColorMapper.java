package simulation.factoryClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Creates map of all possible color combinations (both simulation specific and general) 
 * to be chosen by user input and applied to grid display.
 * 
 * @author Katherine Van Dyk
 * @date 2/11/18
 *
 */
public class ColorMapper {
    private final ResourceBundle COLOR_RESOURCES = 
	    ResourceBundle.getBundle("simulation.color");

    /**
     * @return HashMap<String, String[]> containing color pallete name and String[] of hex color values
     */
    private HashMap<String, String[]> getPallettesMap() {
	HashMap<String, String[]> colors = new HashMap<>();
	for(String scheme : COLOR_RESOURCES.keySet()) {
	    if(!scheme.contains("Default")) colors.put(scheme, COLOR_RESOURCES.getString(scheme).split(" "));
	}
	return colors;
    }
    
    /**
     * @return List<String> containing list of all color palette options (for non-simulation specific coloring options)
     */
    public List<String> getColorOptions() {
	ArrayList<String> retList = new ArrayList<>();
	for(String key : getPallettesMap().keySet()) {
	    retList.add(key);
	}
	return retList;
    }
    
    /**
     * Gets strings of hex values for colors matching the palette name @param color
     */
    public String[] getColors(String color) {
	HashMap<String, String[]> colors = new HashMap<>();
	for(String scheme : COLOR_RESOURCES.keySet()) {
	    colors.put(scheme, COLOR_RESOURCES.getString(scheme).split(" "));
	}
	return colors.get(color);
    }
}

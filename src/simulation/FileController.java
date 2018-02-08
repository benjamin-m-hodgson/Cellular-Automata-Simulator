package simulation;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import configuration.XMLParser;
import simulation.grid.Grid;
import simulation.ruleSet.*;


/**
 * Parses all files and holds grid/ruleset data in respective objects
 * 
 * @author Katherine Van Dyk
 *
 */
public class FileController {

	private String FILEPATH = "./data"; 
	private String EXTENSION = ".xml";
	private Map<String, Grid> grids;  
	private Map<String, Ruleset> rules; 
	
	/**
	 * Constructor
	 */
	public FileController() {
		grids = new HashMap<>();
		rules = new HashMap<>();
	}
	
	
    /**
     * Parse each XML File in directory
     */
    public void parseFiles() {
    		File[] files = getFiles();
		XMLParser p = new XMLParser();
    		for(File file : files) {
    			p.setType(file);
        		grids.put(p.getName(), p.getGrid());	
        		rules.put(p.getName(), p.getRules());
    		}    		
    }
   
	
    /**
     * Get all files in directory
     * Source: https://stackoverflow.com/questions/5751335/using-file-listfiles-with-filenameextensionfilter
     */
    private File[] getFiles() {
    	File directory = new File(FILEPATH);
    	File[] files = directory.listFiles(new FilenameFilter() {
    	    public boolean accept(File dir, String name) {
    	        return name.toLowerCase().endsWith(EXTENSION);
    	    }
    	});
    	return files;
    }
    
    /**
     * Return grids to game driver
     */
    public Map<String, Grid> getGrid() {
    		return grids;
    }
    
    /**
     * Return rules to game driver
     */
    public Map<String, Ruleset> getRules() {
    		return rules;
    }
    
    
}



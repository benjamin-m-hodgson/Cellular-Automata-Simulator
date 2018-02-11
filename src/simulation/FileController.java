package simulation;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import configuration.XMLParsing.XMLParser;
import simulation.grid.Grid;
import simulation.ruleSet.*;

/**
 * Parses all files and holds grid/ruleset data in respective objects
 * 
 * @author Katherine Van Dyk
 *
 */
public class FileController{
    protected String FILEPATH = "./data/xmlFiles/"; 
    protected String EXTENSION = ".xml";
    private Map<String, Grid> grids;  
    private Map<String, Ruleset> rules; 
    private XMLParser parser;

    /**
     * Constructor
     */
    public FileController() {
	grids = new HashMap<>();
	rules = new HashMap<>();
	parser = new XMLParser();
    }

    /**
     * Parse each XML File in directory
     */
    public void parseFiles() {
	File[] files = getFiles();
	for(File file : files) {
	    parser.setType(file);
	    grids.put(parser.getName(), parser.getGrid());	
	    rules.put(parser.getName(), parser.getRuleset());
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
     * Return grids to game engine
     */
    public Map<String, Grid> getGrids() {
	return grids;
    }

    /**
     * Return rules to game engine
     */
    public Map<String, Ruleset> getRules() {
	return rules;
    }
}

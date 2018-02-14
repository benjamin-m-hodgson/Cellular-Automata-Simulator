package configuration;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import configuration.XMLParsing.XMLParser;
import simulation.grid.Grid;
import simulation.ruleSet.*;

/**
 * The purpose of this class is to parse all XML files in the directory "FILEPATH" and return to the Engine maps of all grid
 * and rule objects, with the simulation name serving as the key. It is used during the initial configuration of the program,
 * when the Engine calls an instance of file controller and its parseFiles() method. I think that the file controller serves
 * as a good design because it allows a user to easily switch between simulations by simply clicking a drop down menu rather
 * than navigating a file system and potentially choosing invalid files. The file controller serves as a preliminary error checker
 * by only parsing ".XML" files and encapsulates all details of parsing, handling only the top level organization of different files. 
 * 
 * @author Katherine Van Dyk
 * @date 2/4/18
 */
public class FileController {
    protected String FILEPATH = "./data/xmlFiles/"; 
    protected String EXTENSION = ".xml";
    private Map<String, Grid> GRIDS;  
    private Map<String, Ruleset> RULES; 
    private XMLParser PARSER;

    /**
     * Constructor for File Controller object, initializes GRIDS and RULES as empty hashmaps
     */
    public FileController() {
	GRIDS = new HashMap<>();
	RULES = new HashMap<>();
	PARSER = new XMLParser();
    }

    /**
     * Parses each file in the directory corresponding to FILEPATH and places Grid/Rulesets in 
     */
    public void parseFiles() {
	File[] files = getFiles();
	for(File file : files) {
	    PARSER.initialize(file);
	    GRIDS.put(PARSER.getName(), PARSER.getGrid());
	    RULES.put(PARSER.getName(), PARSER.getRuleset());
	}    		
    }

    /**
     * Returns an array of all file names within the directory specified by FILEPATH ending with EXTENSION
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
     * @return Map<String, Grid>: Containing simulation name as key, and corresponding Grid object as value
     */
    public Map<String, Grid> getGrids() {
	return GRIDS;
    }

    /**
     * @return Map<String, Ruleset>: Containing simulation name as key, and corresponding Ruleset object as value
     */
    public Map<String, Ruleset> getRules() {
	return RULES;
    }
}
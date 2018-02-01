import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import configuration.XMLParser;
import simulation.grid.Grid;
import simulation.ruleSet.*;


/**
 * Parses all files and holds grid/ruleset data in respective objects
 * 
 * @author Katherine Van DYk
 *
 */
public class FileController extends Driver {

	private String FILEPATH = "./data"; 
	private String EXTENSION = ".xml";
	private HashMap<String, Grid> grids;  
	private HashMap<String, Ruleset> rules; 
	
	/**
	 * Constructor
	 */
	public FileController() {
		grids = new HashMap<String, Grid>();
		rules = new HashMap<String, Ruleset>();
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
    
}



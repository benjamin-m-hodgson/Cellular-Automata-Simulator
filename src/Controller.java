

import java.io.File;
import java.io.FilenameFilter;

public class Controller extends Driver {

	private String DIRECTORY = "../data"; 
	private String EXTENSION = ".xml";

	
	
    /**
     * Parse each XML File (may consider making additional control class)
     */
    public void parseFiles() {
    		File[] files = getFile(DIRECTORY, EXTENSION);
    	
    }
	
	
    /**
     * Get all files in directory
     * 
     * @param DIRECTORY
     * @param EXTENSION
     */
    public File[] getFile(String DIRECTORY, String EXTENSION) {
    	File dir = new File(DIRECTORY);
    	File[] files = dir.listFiles(new FilenameFilter() {
    	    public boolean accept(File dir, String name) {
    	        return name.toLowerCase().endsWith(EXTENSION);
    	    }
    	});
    	return files;
    }
}

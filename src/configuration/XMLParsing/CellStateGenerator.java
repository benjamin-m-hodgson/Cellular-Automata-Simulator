package configuration.XMLParsing;
import java.util.Random;

/**
 * Generates cell states based on locations given in XML file or random distribution. Used by the XML
 * Data template to get an integer of array states to later construct the grid with.
 * 
 * @author Katherine Van Dyk
 * @date 2/11/18
 */
public class CellStateGenerator {

    /**
     * Returns int[][] of cell states based on string input of states in XML file
     * 
     * @param s: String of input states
     * @param xSize, ySize: xSize and ySize of grid, respectively
     * @return int[][]: initial states of cells in grid with int[x][y] corresponding to cell[x][y]
     */
    public int[][] locationStates(String s, int xSize, int ySize){
	String[] rows = s.split("\\W+");
	int i = 0;
	int[][] ints = new int[xSize][ySize];
	for(int r= 0; r < ints.length; r++) {
	    for(int c = 0; c < ySize; c++) {
		ints[r][c] = Integer.parseInt(rows[i]);
		i++;
	    }
	}
	return ints;
    }

    /**
     * Chooses cell state randomly based on random number generator and simulation constraints
     * 
     * @param simType: simulation type of XML file being parsed
     * @param xSize, ySize: xSize and ySize of grid, respectively
     * @return int[][]: initial states of cells in grid with int[x][y] corresponding to cell[x][y]
     */
    public int[][] randomStates(int maxStates, int xSize, int ySize) {
	Random rand = new Random();
	int[][] states = new int[xSize][ySize];
	for (int r = 0; r < xSize; r++) {
	    for(int c = 0; c < ySize; c++) {
		states[r][c] = rand.nextInt(maxStates);
	    }
	}
	return states;
    }
}

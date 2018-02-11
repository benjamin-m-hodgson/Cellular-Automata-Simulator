package configuration.XMLParsing;

import java.util.Random;

import configuration.XMLDataFactory;

/**
 * Generates cell states based on locations given in XML file or random distribution
 * 
 * @author Katherine Van Dyk
 * @date 2/11/18
 *
 */
public class CellStateGenerator extends XMLDataFactory {

    /**
     * Returns int[][] of cell states based on string input of states in XML file
     * 
     * @param s: String of input states
     * @param xSize: xSize of grid
     * @param ySize: ySize of grid
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
     * @param xSize: x-size of grid
     * @param ySize: y-size of grid
     * @return int[][]: initial states of cells in grid with int[x][y] corresponding to cell[x][y]
     */
    public int[][] randomStates(String simType, int xSize, int ySize) {
	int upperBound;
	if(simType.equals(getSimulation("FIRE")) || simType.equals(getSimulation("WATOR")) ||
		simType.equals(getSimulation("SEGREGATION"))) {
	    upperBound = 3;
	}
	else {
	    upperBound = 2;
	}
	return arrayGenerator(upperBound, xSize, ySize);
    }

    /**
     * Helper method that uses a random number generator to obtain an array of random states
     * 
     * @param upperBound: highest state used by simulation
     * @param xSize: xSize of grid
     * @param ySize: ySize of grid
     * @return int[][] array of random numbers
     */
    private int[][] arrayGenerator(int upperBound, int xSize, int ySize){
	Random rand = new Random();
	int[][] states = new int[xSize][ySize];
	for (int r = 0; r < xSize; r++) {
	    for(int c = 0; c < ySize; c++) {
		states[r][c] = rand.nextInt(upperBound);
	    }
	}
	return states;
    }
}

package simulation;

import javafx.scene.shape.Shape;
import simulation.cell.*;
import simulation.factoryClasses.ShapeFactory;
import simulation.grid.*;
import simulation.neighborhoods.*;
import simulation.ruleSet.Ruleset;

/**
 * Updates the current grid/ruleset parameters, based on what the engine passes to it.
 * Uses inversion of control through dependency on Engine class methods.
 * 
 * @author Benjamin Hodgson
 * @author Katherine Van Dyk
 * @date 2/8/18
 * 
 */
public class CurrentSimulation {
    protected Engine PROGRAM_ENGINE;
    protected Shape[][] SIMULATION_SHAPES;
    protected boolean EDGE_TYPE;
    protected String SHAPE_TYPE;
    protected String COLOR;
    protected double SHAPE_SIZE;
    protected double SPACE_SIZE;

    /**
     * Holds shape array to be displayed, as well as current grid edge type
     * 
     * @param currentEngine: Current instance of the game engien
     */
    public CurrentSimulation(Engine currentEngine, boolean edge, 
	    String shape, String color, double size, double space) {
	EDGE_TYPE = edge;
	SHAPE_TYPE = shape;
	COLOR = color;
	SHAPE_SIZE = size;
	SPACE_SIZE = space;
	PROGRAM_ENGINE = currentEngine;
	setNeighborManager(shape, edge);
	initializeShapes();
	setColors();
	populateShapes();
    }

    /**
     * Processes cell objects and updates cell sstates, and then cell displays based on new states
     */
    public void update() {
	Grid grid = PROGRAM_ENGINE.currentGrid();
	Ruleset r = PROGRAM_ENGINE.currentRules();
	processCells(grid, r);
	grid.updateStates();
	updateDisplay();
    }

    /**
     * Returns shape display at coordinate (x,y)
     * 
     * @param x: x position of shape in shape array
     * @param y: y position of shape in shape array
     * @return: Shape object to be displayed
     */
    public Shape drawShape(int x, int y) {
	return SIMULATION_SHAPES[x][y];
    }

    /**
     * Creates an initial empty array of shapes to be displayed
     */
    private void initializeShapes() {
	Grid currentGrid = PROGRAM_ENGINE.currentGrid();
	Cell[][] currentCells = currentGrid.getCells();
	SIMULATION_SHAPES = new Shape[currentCells.length][];
	for (int i = 0; i < currentCells.length; i++) {
	    SIMULATION_SHAPES[i] = new Shape[currentCells[i].length];
	}
    }

    /**
     * Takes shape empty array of shapes from initializeShapes() and populates it with proper shape style/color
     */
    private void populateShapes() {
	ShapeFactory shapeFactory = new ShapeFactory(SHAPE_TYPE);
	for (int i = 0; i < SIMULATION_SHAPES.length; i++) {
	    for (int j = 0; j < SIMULATION_SHAPES[i].length; j ++) {
		SIMULATION_SHAPES[i][j] = 
			shapeFactory.chooseShape(i, j, PROGRAM_ENGINE, SHAPE_SIZE, SPACE_SIZE);
	    }
	}
    }

    /**
     * Updates cell states for entire grid based on ruleset
     */
    private void processCells(Grid g, Ruleset r) {
	for(Cell[] row : g.getCells()) {
	    for(Cell cell : row) {
		if(!cell.getMove()) {
		    int newState = r.processCell(cell);
		    cell.setState(newState);
		}
	    }
	}
    }

    /**
     * Updates shape array based on new cell states (located in grid)
     */
    private void updateDisplay() {
	Grid currentGrid = PROGRAM_ENGINE.currentGrid();
	Cell[][] currentCells = currentGrid.getCells();
	for (int i = 0; i < SIMULATION_SHAPES.length; i++) {
	    for (int j = 0; j < SIMULATION_SHAPES[i].length; j++) {
		Shape cell = SIMULATION_SHAPES[i][j];
		cell.setFill(currentCells[i][j].colorCell());
	    }
	}
    }

    /**
     * Sets neighborhood type for current simulation- taking into account cell shape and user input (torodial or finite)
     * 
     * @param shape: Shape type
     * @param finite
     */
    public void setNeighborhood(String shape, boolean finite) {
	SHAPE_TYPE = shape;
	Neighborhood n;
	if(shape.equals("triangle")) n = new TriangleNeighborhood();
	else n = new SquareNeighborhood();
	PROGRAM_ENGINE.currentRules().setNeighborManager(n, finite);
    }

    /**
     * @return Shape type of current display
     */
    public String getShape() {
	return SHAPE_TYPE;
    }

    /**
     * @return Edge type of current grid
     */
    public boolean getEdge() {
	return EDGE_TYPE;
    }

    public void setNeighborManager(String shape, boolean edge) {
	if(shape.equals("triangle")){
	    PROGRAM_ENGINE.currentRules().setNeighborManager(new TriangleNeighborhood(), edge);
	}
	else PROGRAM_ENGINE.currentRules().setNeighborManager(new SquareNeighborhood(), edge);
    }
    
    public String getColor() {
	return COLOR;
    }

    public double getSize() {
	return SHAPE_SIZE;
    }

    public double getSpace() {
	return SPACE_SIZE;
    }
    
    public void setColors() {
	if(PROGRAM_ENGINE.getColors() != null) {
	    PROGRAM_ENGINE.currentGrid().setColors(PROGRAM_ENGINE.getColors());
	}
    }
}

package simulation;

import factoryClasses.ShapeFactory;
import javafx.scene.shape.Shape;
import simulation.cell.*;
import simulation.grid.*;
import simulation.neighborhoods.*;
import simulation.ruleSet.Ruleset;

/**
 * 
 * @author Benjamin Hodgson
 * @date 2/8/18
 * 
 * Updates the current grid/ruleset parameters, based on what the engine passes to it.
 * Uses inversion of control through dependency on Engine class methods.
 * 
 */
public class CurrentSimulation {

    protected Engine PROGRAM_ENGINE;
    protected Shape[][] SIMULATION_SHAPES;
    protected String SHAPE_TYPE;
    protected boolean EDGE_TYPE;
 
    /**
     * Holds shape array to be displayed
     * 
     * @param currentEngine
     */
    public CurrentSimulation(Engine currentEngine, String shape, boolean edge) {
	SHAPE_TYPE = shape;
	EDGE_TYPE = edge;
	PROGRAM_ENGINE = currentEngine;
	initializeShapes();
	populateShapes();
    }

    /**
     * Processes cell objects and updates cell displays based on new states
     */
    public void update() {
	Grid grid = PROGRAM_ENGINE.currentGrid();
	Ruleset r = PROGRAM_ENGINE.currentRules();
	processCells(grid, r);
	grid.updateStates();
	updateDisplay();
    }

    /**
     * Resets shapes to initial states
     */
    public void reset() {
	initializeShapes();
	populateShapes();
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
     * Initializes shape array based on grid size and cell initial states
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
     * Populates shapes on grid
     */
    private void populateShapes() {
	ShapeFactory shapeFactory = new ShapeFactory(SHAPE_TYPE);
	for (int i = 0; i < SIMULATION_SHAPES.length; i++) {
	    for (int j = 0; j < SIMULATION_SHAPES[i].length; j ++) {
		SIMULATION_SHAPES[i][j] = 
			shapeFactory.chooseShape(i, j, PROGRAM_ENGINE);
	    }
	}
    }

    /**
     * Returns new cell states for entire grid
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
     * Updates shape objects based on cell states
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
    
    public void setNeighborhood(String shape, boolean finite) {
	SHAPE_TYPE = shape;
	Neighborhood n;
	// MAKE TRIANGLE
	if(shape.equals("triangle")) n = new SquareNeighborhood();
	else n = new SquareNeighborhood();
	PROGRAM_ENGINE.currentRules().setNeighborManager(n, finite);
    }
    
    public String getShape() {
	return SHAPE_TYPE;
    }
    
    public boolean getEdge() {
	return EDGE_TYPE;
    }
}

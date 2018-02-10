package simulation;

import javax.xml.transform.TransformerConfigurationException;

import configuration.XMLWriter;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import simulation.cell.*;
import simulation.grid.*;
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

    private final double DEFAULT_SPACING = 1.0;
    private final double DEFAULT_INDICATOR = -1;

    private Engine PROGRAM_ENGINE;
    private Shape[][] SIMULATION_SHAPES;

    // holds only the shape array, is passed the grid/ruleset or naw
    public CurrentSimulation(Engine currentEngine) {
        PROGRAM_ENGINE = currentEngine;
        initializeShapes();
        populateShapes();
    }

    public void update() {
        Grid grid = PROGRAM_ENGINE.currentGrid();
        Ruleset r = PROGRAM_ENGINE.currentRules();
        processCells(grid, r);
        updateStates(grid);
        updateDisplay();
    }

    public void reset() {
        initializeShapes();
        populateShapes();
    }

    public Shape drawShape(int x, int y) {
        return SIMULATION_SHAPES[x][y];
    }	

    public void makeNewXML() {
        XMLWriter writer = new XMLWriter();
        try {
            writer.createDoc("Fire", "hello", 
                    PROGRAM_ENGINE.currentGrid(), PROGRAM_ENGINE.currentRules());
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void initializeShapes() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        Cell[][] currentCells = currentGrid.getCells();
        SIMULATION_SHAPES = new Shape[currentCells.length][];
        for (int i = 0; i < currentCells.length; i++) {
            SIMULATION_SHAPES[i] = new Shape[currentCells[i].length];
        }
    }

    private void populateShapes() {
        String currentShape = PROGRAM_ENGINE.currentShapeType();
        for (int i = 0; i < SIMULATION_SHAPES.length; i++) {
            for (int j = 0; j < SIMULATION_SHAPES[i].length; j ++) {
                if (currentShape.equalsIgnoreCase("Rectangle")) {
                    RectangleHandler shapeHandler = new RectangleHandler(PROGRAM_ENGINE,
                            DEFAULT_INDICATOR, DEFAULT_INDICATOR, DEFAULT_SPACING);
                    Rectangle cellShape = shapeHandler.generateRectangle(i, j);
                    System.out.println(cellShape);
                    cellShape.setId("defaultCell");
                    SIMULATION_SHAPES[i][j] = cellShape;
                }
                /*else if (currentShape.equalsIgnoreCase("Triangle")) {
					Polygon triangleCell = generateTriangle(i, j);
					SIMULATION_SHAPES[i][j] = triangleCell;
				}*/
            }
        }
    }

    /**
     * **How do we get grid to it
     * Returns new cell states for entire grid
     */
    private void processCells(Grid g, Ruleset r) {
        Cell[][] cellsToProcess = g.getCells();
        for (int i = 0; i < cellsToProcess.length; i++) {
            for (int j = 0; j < cellsToProcess[i].length; j++) {
                Cell cell = cellsToProcess[i][j];
                if(cell.getMove()) {
                    continue;
                }
                else {
                    int newState = r.processCell(cell);
                    cell.setState(newState);
                }
            }
        }
    }

    /**
     * Updates states for all cells at once
     */
    private void updateStates(Grid g) {
        for(Cell[] row : g.getCells()) {
            for(Cell cell : row) {
                cell.updateState();
                cell.setMove(false);
            }
        }
    }

    private void updateDisplay() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        Cell[][] currentCells = currentGrid.getCells();
        for (int i = 0; i < currentCells.length 
                && i < SIMULATION_SHAPES.length; i++) {
            for (int j = 0; j < currentCells[i].length 
                    && j < SIMULATION_SHAPES[i].length; j++) {
                Shape cell = SIMULATION_SHAPES[i][j];
                cell.setFill(currentCells[i][j].colorCell());
            }
        }
    }

    /**
     * Gets cell count of particular state for graph datapoints 
     * 
     * @param g
     * @param state
     * @return
     */
    public int stateCount(Grid g, int state) {
        int count = 0;
        for(Cell[] row : g.getCells()) {
            for(Cell cell : row) {
                if(cell.getState() == state) {
                    count++;
                }
            }
        }
        return count;
    }
}

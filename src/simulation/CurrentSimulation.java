package simulation;

import javax.xml.transform.TransformerConfigurationException;

import configuration.XMLWriter;
import javafx.beans.binding.Bindings;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import simulation.cell.*;
import simulation.grid.*;
import simulation.ruleSet.Ruleset;

/**
 * Updates the current grid/ruleset parameters, based on what the engine passes to it.
 * Uses inversion of control through dependency on Engine class methods.
 * 
 */
public class CurrentSimulation {

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
			writer.createDoc("Fire", "hello", PROGRAM_ENGINE.currentGrid(), PROGRAM_ENGINE.currentRules());
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
					Rectangle cellShape = drawRectangle();
					SIMULATION_SHAPES[i][j] = cellShape;
				}
				else if (currentShape.equalsIgnoreCase("Triangle")) {
					Polygon triangleCell = drawTriangle(j);
					SIMULATION_SHAPES[i][j] = triangleCell;
				}
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
				SIMULATION_SHAPES[i][j].setFill(currentCells[i][j].colorCell());
			}
		}
	}
	
	private Rectangle drawRectangle() {
	    Rectangle cellShape = new Rectangle();
         cellShape.setId("defaultCell");
         cellShape.heightProperty().bind(
                   Bindings.divide(PROGRAM_ENGINE.sceneHeight(), 
                             1.2*SIMULATION_SHAPES.length));
         cellShape.widthProperty().bind(
                   Bindings.divide(PROGRAM_ENGINE.sceneWidth(), 
                             1.5*SIMULATION_SHAPES.length));
         return cellShape;
	}
	
	private Polygon drawTriangle(int col) {
         Polygon cellShape = new Polygon();
         cellShape.getPoints().addAll(new Double[] {
                 15.0, 0.0,
                 0.0, 15.0,
                 30.0, 15.0,
         });
         if (col % 2 != 0) {
             cellShape.setTranslateX(cellShape.getLayoutX());
             cellShape.setTranslateY(cellShape.getLayoutY());
             cellShape.getTransforms().add(new Rotate(180, 0, 0));
         }
         cellShape.setId("defaultCell");
         
         return cellShape;
     }
}

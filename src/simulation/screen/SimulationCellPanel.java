package simulation.screen;

import javafx.beans.binding.Bindings;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import simulation.CurrentSimulation;
import simulation.Engine;
import simulation.cell.Cell;
import simulation.grid.Grid;

/**
 * 
 * @author Benjamin Hodgson
 * @date 2/8/18
 * 
 * Class to generate the cell panel to be displayed on the center of the simulation screen.
 * The cell panel child nodes are held in a VBox object.
 */
public class SimulationCellPanel {
	
	private final Engine PROGRAM_ENGINE;
	private final CurrentSimulation SIMULATION;
	
	private HBox CELL_PANEL;
	
	public SimulationCellPanel(Engine programEngine, CurrentSimulation simulation) {
		PROGRAM_ENGINE = programEngine;
		SIMULATION = simulation;
		CELL_PANEL = cellPanel();
	}
	
	public HBox construct() {
		return CELL_PANEL;
	}
	
	/**
	 * 
	 * @return cellPanel: the Panel that contains the cell objects
	 */
	private HBox cellPanel() {
		HBox cellPanel = new HBox();
		cellPanel.setId("simulateCellPanel");
		cellPanel.prefHeightProperty().bind(Bindings.divide(PROGRAM_ENGINE.sceneHeight(), 1.0));
		addCells(cellPanel);
		return cellPanel;
	}
	
	/**
	 * A panel of that contains the Cell objects
	 * 
	 * @param cellPanel: the Panel that contains the cell Objects
	 */
	private void addCells(HBox cellPanel) {
		Grid typeGrid = null;
		try {
			typeGrid = PROGRAM_ENGINE.getGrid(PROGRAM_ENGINE.getSimulationType());
		}
		catch (NullPointerException e) {
			System.out.printf("Null argument received from getGrid() in "
					+ "SimulationScreen class\n");
		}
		Cell[][] simulationCells = typeGrid.getCells();
		//System.out.println(simulationCells);
		VBox cols = new VBox(1);
		for (int i = 0; i < simulationCells.length; i++) {
			HBox row = new HBox(1);
			for (int j = 0; j < simulationCells[i].length; j++) {
				Shape cellShape = SIMULATION.drawShape(i, j);
				row.getChildren().add(cellShape);
			}
			cols.getChildren().add(row);
		}
		// add a region to align the cell grid in the top left of the cellPanel
		cellPanel.getChildren().add(cols);
		Region fillerRegion = new Region();
		HBox.setHgrow(fillerRegion, Priority.ALWAYS);
		cellPanel.getChildren().add(fillerRegion);
	}
}

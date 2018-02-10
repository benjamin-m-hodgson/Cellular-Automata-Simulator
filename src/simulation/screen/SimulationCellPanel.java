package simulation.screen;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
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

    private Pane CELL_PANEL;

    public SimulationCellPanel(Engine programEngine, CurrentSimulation simulation) {
        PROGRAM_ENGINE = programEngine;
        SIMULATION = simulation;
        CELL_PANEL = cellPanel();
        CELL_PANEL.setId("simulatCellPanel");
    }

    public ScrollPane construct() {
        ScrollPane retPane = new ScrollPane(CELL_PANEL);
        retPane.setId("simulateCellPanel");
        retPane.prefHeightProperty().bind(PROGRAM_ENGINE.sceneHeight());
        retPane.prefWidthProperty().bind(PROGRAM_ENGINE.sceneWidth());
        return retPane;
    }

    /**
     * 
     * @return cellPanel: the Panel that contains the cell objects
     */
    private Pane cellPanel() {
        Pane cellPanel = new Pane();
        addCells(cellPanel);
        return cellPanel;
    }

    /**
     * A panel of that contains the Cell objects
     * 
     * @param cellPanel: the Panel that contains the cell Objects
     */

    private void addCells(Pane cellPanel) {
        Grid typeGrid = PROGRAM_ENGINE.getGrid(PROGRAM_ENGINE.getSimulationType());
        Cell[][] simulationCells = typeGrid.getCells();
        for (int i = 0; i < simulationCells.length; i++) {
            for (int j = 0; j < simulationCells[i].length; j++) {
                Node cellShape = SIMULATION.drawShape(i, j);
                cellPanel.getChildren().add(cellShape);
            }
        }
    }
}

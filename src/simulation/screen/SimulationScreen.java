package simulation.screen;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import simulation.CurrentSimulation;
import simulation.Engine;
import simulation.screen.panels.SimulationCellPanel;
import simulation.screen.panels.SimulationGraphPanel;
import simulation.screen.panels.SimulationControlPanel;

/**
 * 
 * @author Benjamin Hodgson
 * @date 2/1/2018
 *
 * Creates the root object to be placed in the simulation Scene. 
 * 
 */
public class SimulationScreen extends Screen {
    
    private final CurrentSimulation SIMULATION;
    private SimulationControlPanel CONTROL_PANEL;
    private SimulationCellPanel CELL_PANEL;
    private SimulationGraphPanel GRAPH_PANEL;
    private BorderPane ROOT_PANE;

    // need to save the Engine to call functions on button clicks
    public SimulationScreen(Engine programEngine, CurrentSimulation simulation) {
        super(programEngine);
        SIMULATION = simulation;
        CONTROL_PANEL = new SimulationControlPanel(PROGRAM_ENGINE, this);
        CELL_PANEL = new SimulationCellPanel(PROGRAM_ENGINE, SIMULATION);
        GRAPH_PANEL = new SimulationGraphPanel(PROGRAM_ENGINE);
    }

    @Override
    public void makeRoot() {
        Parent controlPanel = CONTROL_PANEL.construct();
        ROOT_PANE = new BorderPane();
        ROOT_PANE.setCenter(getCenterPane());
        ROOT_PANE.setRight(controlPanel);
        ROOT_PANE.setId("simulateScreenRoot");
        ROOT = ROOT_PANE;
        // attach "animation loop" to time line to play it
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();     
    }

    /**
     * Creates stacked cell grid and graph
     * 
     * @return
     */
    public BorderPane getRootPane() {
	if (ROOT_PANE == null) {
	    makeRoot();
	}
        return ROOT_PANE;
    }

    /**
     * Creates stacked cell grid and graph
     * 
     * @return
     */
    private BorderPane getCenterPane() {
        BorderPane CellGraph = new BorderPane();
        ScrollPane cellPanel = CELL_PANEL.construct();
        cellPanel.setId("simulateCellPanel");
        HBox graph = GRAPH_PANEL.construct();
        graph.setId("simulateGraph");
        CellGraph.setCenter(cellPanel);
        CellGraph.setBottom(graph);
        return CellGraph;
    }

    /**
     * Change properties of displayed items to reflect animation properties
     * 
     * @param elapsedTime: time since last animation update
     */
    private void step (double elapsedTime) {
        CONTROL_PANEL.update();
        GRAPH_PANEL.update();
    }
}

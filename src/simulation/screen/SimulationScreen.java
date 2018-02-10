package simulation.screen;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import simulation.CurrentSimulation;
import simulation.Engine;

/**
 * 
 * @author Benjamin Hodgson
 * @date 2/1/2018
 *
 * Creates the root object to be placed in the simulation Scene. 
 * 
 */
public class SimulationScreen extends Screen {

	private final int FRAMES_PER_SECOND = 60;
	private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private final CurrentSimulation SIMULATION;

	private SimulationControlPanel CONTROL_PANEL;
	private SimulationCellPanel CELL_PANEL;
	private SimulationGraphPanel GRAPH_PANEL;

	// need to save the Engine to call functions on button clicks
	public SimulationScreen(Engine programEngine, CurrentSimulation simulation) {
		super(programEngine);
		SIMULATION = simulation;
		CONTROL_PANEL = new SimulationControlPanel(PROGRAM_ENGINE);
		CELL_PANEL = new SimulationCellPanel(PROGRAM_ENGINE, SIMULATION);
		GRAPH_PANEL = new SimulationGraphPanel(PROGRAM_ENGINE);
	}

	@Override
	public void makeRoot() {
		VBox controlPanel = CONTROL_PANEL.construct();
		BorderPane newRoot = new BorderPane();
		newRoot.setCenter(this.getCenterPane());
		newRoot.setRight(controlPanel);
		newRoot.setId("simulateScreenRoot");
		ROOT = newRoot;
		
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
	private BorderPane getCenterPane() {
		BorderPane CellGraph = new BorderPane();
		ScrollPane cellPanel = CELL_PANEL.construct();
		HBox graph = GRAPH_PANEL.construct();
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

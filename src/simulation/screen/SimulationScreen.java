package simulation.screen;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
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
	private final Engine PROGRAM_ENGINE;
	
	private int GENERATION;
	
	// need to save the Engine to call functions on button clicks
	public SimulationScreen(Engine programEngine) {
		PROGRAM_ENGINE = programEngine;
	}
	
	@Override
	public void makeRoot() {
		VBox sidePanel = sidePanel();
		BorderPane newRoot = new BorderPane();
		newRoot.setRight(sidePanel);
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

	// create an info Label to be placed in the side panel in the simulation screen
	@Override
	public Label makeLabel(String text) {
		Label infoLabel = new Label(text);
		infoLabel.setId("simulationInfoLabel");
		return infoLabel;
	}

	@Override
	public Button makeButton(String text) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	private VBox sidePanel() {
		VBox simulationInfo = makeInfo();
		VBox sidePanel = new VBox(50);
		sidePanel.setId("simulateSidePanel");
		sidePanel.prefHeightProperty().bind(Bindings.divide(PROGRAM_ENGINE.getProgramStage()
				.heightProperty(), 1.0));
		sidePanel.getChildren().add(simulationInfo);
		return sidePanel;
	}
	
	/**
	 * 
	 * @return
	 */
	private VBox makeInfo() {
		Label currentSimulation = makeLabel("Current Simulation:");
		Label simulationName = makeLabel(PROGRAM_ENGINE.getSimulationType());
		Label currentGeneration = makeLabel("Current Generation:");
		Label generationNum = makeLabel(Integer.toString(GENERATION));
		VBox simulationInfo = new VBox(10, currentSimulation, simulationName,
				currentGeneration, generationNum);
		return simulationInfo;
	}
	
	/**
	 * Change properties of shapes to animate them. In this instance,
	 * primarily updates @param GENERATION to reflect the current generation
	 * in the simulation. 
	 * 
	 * @param elapsedTime: time since last animation update
	 */
    private void step (double elapsedTime) {
    	GENERATION = PROGRAM_ENGINE.getGeneration();
    }
}

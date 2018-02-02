package simulation.screen;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
	private String TYPE;
	private boolean VALID;
	private Button SIMULATE;
	private Button PLAY;
	private Button PAUSE;
	
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

	// make a simulate button
	@Override
	public Button makeButton(String text) {
		Button simulateButton = new Button(text);
		simulateButton.setId("simulateButton");
		// handle click event
		simulateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				PROGRAM_ENGINE.startSimulation(TYPE);	
			}
		});
		simulateButton.setDisable(true);
		return simulateButton;
	}
	
	/**
	 * Creates a drop down menu that changes the value of the instance 
	 * variable @param TYPE upon selection. When a valid type is selected 
	 * from the choices, the boolean @param VALID is flagged true. 
	 * 
	 * @return dropDownMenu: a drop down menu that lets the user choose a 
	 * simulation to simulate
	 */
	private ChoiceBox<Object> simulatorChooser() {
		ChoiceBox<Object> dropDownMenu = new ChoiceBox<Object>();
		String defaultChoice = "Select a Simulation";
		dropDownMenu.setValue(defaultChoice);
		ObservableList<Object> simulationChoices = 
				FXCollections.observableArrayList(defaultChoice, new Separator());
		simulationChoices.addAll(PROGRAM_ENGINE.getSimulations());
		dropDownMenu.setItems(simulationChoices);
		dropDownMenu.setValue(PROGRAM_ENGINE.getSimulationType());
		dropDownMenu.setId("simulatorChooser");
		dropDownMenu.getSelectionModel().selectedIndexProperty()
        .addListener(new ChangeListener<Number>() {
		@Override
		public void changed(ObservableValue<? extends Number> arg0, 
				Number arg1, Number arg2) {
			TYPE = (String) simulationChoices.get((Integer) arg2);
			VALID = !(TYPE.equals(defaultChoice) ||
					TYPE.equals(PROGRAM_ENGINE.getSimulationType()));	
		}
		});
		return dropDownMenu;
	}
		
	
	/**
	 * 
	 * @return
	 */
	private VBox sidePanel() {
		VBox simulationInfo = makeInfo();
		VBox simulationMenu = makeMenu();
		VBox simulationSettings = makeSettings();
		VBox sidePanel = new VBox(50, simulationInfo, simulationMenu, simulationSettings);
		sidePanel.setId("simulateSidePanel");
		sidePanel.prefHeightProperty().bind(Bindings.divide(PROGRAM_ENGINE.getProgramStage()
				.heightProperty(), 1.0));
		return sidePanel;
	}
	
	/**
	 * Creates the section of the side panel that contains information about the current
	 * simulation. 
	 * 
	 * @return simulationInfo: a VBox containing the simulation information to be displayed
	 */
	private VBox makeInfo() {
		Label currentSimulation = makeLabel("Current Simulation:");
		Label simulationName = makeLabel(PROGRAM_ENGINE.getSimulationType());
		Label currentGeneration = makeLabel("Current Generation:");
		Label generationNum = makeLabel(Integer.toString(GENERATION));
		VBox simulationInfo = new VBox(5, currentSimulation, simulationName,
				currentGeneration, generationNum);
		return simulationInfo;
	}
	
	/**
	 * Creates the component of the side panel that allows for simulation change. The 
	 * drop down choice box and the simulate button are identical to the ones in the 
	 * StartScreen class.
	 * 
	 * 
	 * @return simulationMenu: a VBox containing controls to change the animation
	 */
	private VBox makeMenu() {
		Label simulationPrompt = makeLabel("Change Simulation:"); 
		ChoiceBox<Object> simulationChoices = simulatorChooser();
		SIMULATE = makeButton("Simulate");
		VBox simulationMenu = new VBox(5, simulationPrompt, simulationChoices, SIMULATE);
		return simulationMenu;
	}
	
	/**
	 * 
	 * @return
	 */
	private VBox makeSettings() {
		PLAY = makePlayButton();
		PAUSE = makePauseButton();
		Button resetButton = makeResetButton();
		Button stepButton = makeStepButton();
		HBox topButtonRow = new HBox(20, PLAY, PAUSE);
		HBox bottomButtonRow = new HBox(20, resetButton, stepButton);
		VBox simulationSettings = new VBox(20, topButtonRow, bottomButtonRow);
		simulationSettings.setId("simulationSettings");
		return simulationSettings;
	}
	
	/**
	 * Creates a play button to be displayed in the side panel to begin playing a paused
	 * animation. 
	 * 
	 * @return playButton: the button to restart or play a paused simulation
	 */
	private Button makePlayButton() {
		Button playButton = new Button();
		playButton.setId("playButton");
		playButton.setDisable(true);
		// handle click event
		playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				PAUSE.setDisable(false);
				PLAY.setDisable(true);
				PROGRAM_ENGINE.playAnimation();
			}
		});
		return playButton;
	}
	
	/**
	 * Creates a pause button to be displayed in the side panel to pause playing an
	 * animation. 
	 * 
	 * @return pauseButton: the button to pause a running simulation
	 */
	private Button makePauseButton() {
		Button pauseButton = new Button();
		pauseButton.setId("pauseButton");
		pauseButton.setDisable(false);
		// handle click event
		pauseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				PAUSE.setDisable(true);
				PLAY.setDisable(false);
				PROGRAM_ENGINE.pauseAnimation();
			}
		});
		return pauseButton;
	}
	
	/**
	 * Creates a reset button to be displayed in the side panel to reset a playing 
	 * animation from the beginning with initial parameters and properties.  
	 * 
	 * @return resetButton: the button to reset a running simulation from its initial state
	 */
	private Button makeResetButton() {
		Button resetButton = new Button();
		resetButton.setId("resetButton");
		// handle click event
		resetButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				PROGRAM_ENGINE.startSimulation(PROGRAM_ENGINE.getSimulationType());
			}
		});
		return resetButton;
	}
	
	/**
	 * Creates a step button to be displayed in the side panel to step forward one frame 
	 * in the animation.
	 * 
	 * @return stepButton: the button to step one frame through an animation.
	 */
	private Button makeStepButton() {
		Button stepButton = new Button();
		stepButton.setId("stepButton");
		// handle click event
		stepButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				PROGRAM_ENGINE.singleStep();
				PAUSE.setDisable(true);
				PLAY.setDisable(false);
			}
		});
		return stepButton;
	}
	
	/**
	 * Change properties of shapes to animate them. In this instance,
	 * primarily updates @param GENERATION to reflect the current generation
	 * in the simulation and checks to see if another simulation was selected.
	 * 
	 * @param elapsedTime: time since last animation update
	 */
    private void step (double elapsedTime) {
    	GENERATION = PROGRAM_ENGINE.getGeneration();
    	if (VALID && SIMULATE.isDisabled()) {
    		SIMULATE.setDisable(false);
    	}
    	else if (!VALID && !SIMULATE.isDisabled()) {
    		SIMULATE.setDisable(true);
    	}
    }
}

package simulation.screen.panels;

import java.text.DecimalFormat;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import simulation.CurrentSimulation;
import simulation.Engine;
import simulation.screen.SimulationScreen;
import simulation.screen.SimulationSettings;

/**
 * 
 * @author Benjamin Hodgson
 * @date 2/8/18
 * 
 * Class to generate the control panel to be displayed on the side of the simulation screen.
 * The control panel child nodes are held in a VBox object.
 */
public class SimulationControlPanel {

    private final int GROUP_SPACING = 50;
    private final int LABEL_SPACING = 5;
    private final int BUTTON_SPACING = 20;
    private final double SPEED_MIN = 0.25;
    private final double SPEED_MAX = 2;

    private Parent CONTROL_PANEL;
    private Engine PROGRAM_ENGINE;
    private SimulationScreen ROOT_SCREEN;

    private Label GENERATION;
    private Label SPEED;
    private Button SIMULATE;
    private Button PLAY;
    private Button PAUSE;



    public SimulationControlPanel(Engine programEngine, SimulationScreen rootScreen) {
	PROGRAM_ENGINE = programEngine;
	ROOT_SCREEN = rootScreen;
	CONTROL_PANEL = sidePanel();
    }

    public Parent construct() {
	return CONTROL_PANEL;
    }

    /**
     * Updates the information displayed in the control panel. Primarily 
     * updates @param GENERATION to reflect the current generation
     * in the simulation and checks to see if another simulation was selected.
     */
    public void update() {
	GENERATION.setText(Integer.toString(PROGRAM_ENGINE.getGeneration()));
    }

    /**
     * Method to create and return the side panel that contains information about the 
     * current simulation and gives the user some control buttons to manipulate
     * the simulation animation. 
     * 
     * @return sidePanel: the panel on the side with information and animation controls
     */
    private VBox sidePanel() {
	VBox simulationInfo = makeInfo();
	VBox simulationMenu = makeMenu();
	VBox simulationSettings = makeSettings();
	VBox sidePanel = new VBox(GROUP_SPACING, simulationInfo, 
		simulationMenu, simulationSettings);
	sidePanel.setId("simulateSidePanel");
	sidePanel.prefHeightProperty().bind(Bindings.divide(PROGRAM_ENGINE.sceneHeight(), 1.0));
	return sidePanel;
    }

    /**
     * Creates the section of the side panel that contains information about the current
     * simulation. 
     * 
     * @return simulationInfo: a VBox containing the simulation information to be displayed
     */
    private VBox makeInfo() {
	Label currentSimulation = makeInfoLabel(PROGRAM_ENGINE.
		resourceString("currentSimulationString"));
	Label simulationName = makeInfoLabel(PROGRAM_ENGINE.getSimulationName());
	Label currentGeneration = makeInfoLabel(PROGRAM_ENGINE.
		resourceString("currentGenerationString"));
	GENERATION = makeInfoLabel(Integer.toString(PROGRAM_ENGINE.getGeneration()));
	VBox simulationInfo = new VBox(LABEL_SPACING, currentSimulation, simulationName,
		currentGeneration, GENERATION);
	return simulationInfo;
    }

    /**
     * 
     * @param text: text to be placed on the label
     * @return infoLabel to be placed in the side panel in the simulation screen
     */
    private Label makeInfoLabel(String text) {
	Label infoLabel = new Label(text);
	infoLabel.setId("simulationInfoLabel");
	return infoLabel;
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
	Label simulationPrompt = makeInfoLabel(PROGRAM_ENGINE.
		resourceString("changeSimulationString")); 
	SIMULATE = makeButton(PROGRAM_ENGINE.resourceString("newSimulateString"));
	VBox simulationMenu = new VBox(LABEL_SPACING, simulationPrompt, SIMULATE);
	return simulationMenu;
    }

    /**
     * Make button to bring up simulation settings panel to simulation styling screen
     * 
     * @param text: Text to put in button
     * @return Button
     */
     public Button makeButton(String text) {
         Button simulateButton = new Button(text);
         simulateButton.setId("newSimulationButton");
         simulateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent arg0) {
        	 VBox newPanel = new VBox(LABEL_SPACING,
        		 new SimulationSettings(PROGRAM_ENGINE).getRoot(),
        		 exitButton());
        	 newPanel.setId("simulationSettingsRoot");
                 ROOT_SCREEN.getRootPane().setRight(newPanel);
             }
         });
         return simulateButton;
     }
     
     /**
      * Make button to bring up control settings panel to simulation styling screen
      * 
      * @return Button
      */
     public Button exitButton() {
	 Button exitButton = new Button(PROGRAM_ENGINE.resourceString("backPrompt"));
	 exitButton.setId("simulateButton");
         exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent arg0) {
        	 ROOT_SCREEN.getRootPane().setRight(CONTROL_PANEL);
             }
         });
	 return exitButton;
     }

    /**
     * Creates the section of the side panel with the play, pause, reset, step, and speed
     * animation controls. 
     * 
     * @return simulationSettings: the bottom section of the side panel with animation 
     * controls
     */
    private VBox makeSettings() {
	PLAY = makePlayButton();
	PAUSE = makePauseButton();
	Button resetButton = makeResetButton();
	Button stepButton = makeStepButton();
	Slider speedSlider = makeSlider();
	SPEED = makeSliderLabel();
	HBox topButtonRow = new HBox(BUTTON_SPACING, PLAY, PAUSE);
	HBox middleButtonRow = new HBox(BUTTON_SPACING, resetButton, stepButton);
	HBox bottomRow = simulationWriter();
	VBox simulationSettings = new VBox(BUTTON_SPACING, topButtonRow, middleButtonRow, bottomRow,
		speedSlider, SPEED);
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
		CurrentSimulation simulation = PROGRAM_ENGINE.getCurrentSimulation();
                PROGRAM_ENGINE.startSimulation(PROGRAM_ENGINE.getSimulationName(), 
                	simulation.getEdge(), simulation.getShape(), simulation.getColor(),
                	simulation.getSize(), simulation.getSpace());
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
     * Creates a save button to be displayed in the side panel to save the current 
     * grid as an XML file
     * 
     * @return saveButton: the button to step one frame through an animation.
     */
    private HBox simulationWriter() {
	TextField textField = new TextField();
	textField.setText("Enter Filename");
	Button saveButton = new Button();
	saveButton.setId("saveButton");
	// handle click event
	saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent arg0) {
		PROGRAM_ENGINE.writeGridtoXML(textField.getText());
	    }
	});
	HBox writer = new HBox(textField, saveButton);
	return writer;
    }

    /**
     * Slider that adjusts the number of generations animated per second in the 
     * simulation.
     * 
     * @return speedSlider: a Slider to change the generation speed
     */
    private Slider makeSlider() {
	Slider speedSlider = new Slider(SPEED_MIN, SPEED_MAX, 1);
	speedSlider.setId("simulateSpeedSlider");
	speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
	    @Override
	    public void changed(ObservableValue<? extends Number> arg0, 
		    Number arg1, Number arg2) {
		PROGRAM_ENGINE.setGenerationSpeed((double) arg2);
		DecimalFormat twoPlaces = new DecimalFormat("#.00");
		String speedArgs = twoPlaces.format(arg2);
		String speedText = "Animation Speed: " + speedArgs;
		SPEED.setText(speedText);
	    }
	});
	return speedSlider;
    }

    /**
     * Creates a Label object that displays the current speed of the animation with
     * respect to the number of generations processed per second.
     * 
     * @return sliderLabel: a Label indicating the current selected generation speed
     */
    private Label makeSliderLabel() {
	Label sliderLabel = new Label(PROGRAM_ENGINE.
		resourceString("animationSpeedString") + " 1.00");
	sliderLabel.setId("simulateSpeedLabel");
	return sliderLabel;
    }
}

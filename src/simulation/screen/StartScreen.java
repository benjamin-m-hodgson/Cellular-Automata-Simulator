package simulation.screen;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import simulation.Engine;

/**
 * 
 * @author Benjamin Hodgson
 * @date 2/1/2018
 *
 * Creates the root object to be placed in the start up Scene. 
 * This consists of a program title, a drop down menu to select a 
 * simulation to simulate, and a simulate button to begin the simulation.
 *
 */
public class StartScreen extends Screen {
	
	private final int FRAMES_PER_SECOND = 60;
	private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private final Engine PROGRAM_ENGINE;
	
	private String TYPE;
	private boolean VALID;
	private Button SIMULATE;
	
	// need to save the Engine to call functions on button clicks
	public StartScreen(Engine programEngine) {
		PROGRAM_ENGINE = programEngine;
	}

	// make the root for the start screen to display on application start up
	@Override
	public void makeRoot() {
		Label programTitle = makeLabel(PROGRAM_ENGINE.resourceString("programTitleString"));
		ChoiceBox<Object> simulationChoices = simulatorChooser();
		SIMULATE = makeButton(PROGRAM_ENGINE.resourceString("simulateString"));
		HBox alignBoxes = new HBox(30, simulationChoices, SIMULATE);
		VBox alignTitle = new VBox(30, programTitle, alignBoxes);
     	GridPane newRoot = new GridPane();
     	newRoot.setId("startScreenRoot");
     	newRoot.getChildren().add(alignTitle);
		ROOT = newRoot;
		// attach "animation loop" to time line to play it
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();	
	}
	
	// assign the node Id and use css document to format
	public Label makeLabel(String text) {
		Label textLabel = new Label(text);
		textLabel.setId("startLabel");
		return textLabel;
		
	}

	// make a simulate button
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
		ChoiceBox<Object> dropDownMenu = new ChoiceBox<>();
		String defaultChoice = PROGRAM_ENGINE.resourceString("defaultChooserString");
		dropDownMenu.setValue(defaultChoice);
		ObservableList<Object> simulationChoices = 
				FXCollections.observableArrayList(defaultChoice, new Separator());
		simulationChoices.addAll(PROGRAM_ENGINE.getSimulations());
		dropDownMenu.setItems(simulationChoices);
		dropDownMenu.setId("simulatorChooser");
		dropDownMenu.getSelectionModel().selectedIndexProperty()
        .addListener(new ChangeListener<Number>() {
		@Override
		public void changed(ObservableValue<? extends Number> arg0, 
				Number arg1, Number arg2) {
			TYPE = (String) simulationChoices.get((Integer) arg2);
			VALID = !(TYPE.equals(defaultChoice));	
		}
		});
		return dropDownMenu;
	}
	
	/**
	 * Change properties of shapes to animate them. In this instance,
	 * primarily checks to see if the selected simulation is valid and
	 * updates the @param SIMULATE button to reflect @param VALID. 
	 * 
	 * @param elapsedTime: time since last animation update
	 */
    private void step (double elapsedTime) {
    	if (VALID && SIMULATE.isDisabled()) {
    		SIMULATE.setDisable(false);
    	}
    	else if (!VALID && !SIMULATE.isDisabled()) {
    		SIMULATE.setDisable(true);
    	}
    }
}

package simulation.screen;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import simulation.Engine;

public class SimulationSettings extends Screen {

    private final int VERTICAL_SPACING = 20;
    private final int LABEL_SPACING = 5;
    private final int FRAMES_PER_SECOND = 60;
    private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private String DEFAULT_INDICATOR;
    private String SIMULATION;
    private boolean SIMULATION_VALID;
    private String EDGE;
    private boolean EDGE_VALID;
    private String SHAPE;
    private boolean SHAPE_VALID;
    private Button SIMULATE;

    public SimulationSettings(Engine programEngine) {
	super(programEngine);
	DEFAULT_INDICATOR = PROGRAM_ENGINE.resourceString("defaultString");
	SIMULATION = DEFAULT_INDICATOR;
	SHAPE = DEFAULT_INDICATOR;
    }

    @Override
    public void makeRoot() {
	VBox simulationStyles = makeStylePanel();
	simulationStyles.setId("simulationSettingsRoot");
	ROOT = simulationStyles;
	// attach "animation loop" to time line to play it
	KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
		e -> step(SECOND_DELAY));
	Timeline animation = new Timeline();
	animation.setCycleCount(Timeline.INDEFINITE);
	animation.getKeyFrames().add(frame);
	animation.play();     
    }
    
    private VBox makeStylePanel() {
	ComboBox<Object> simulationChoices = simulatorChooser();
	ComboBox<Object> shapeChoices = shapeChooser();
	ComboBox<Object> edgeChoices = edgeChooser();
	//VBox cellSizing = cellSizeOptions();
	SIMULATE = makeButton(PROGRAM_ENGINE.resourceString("simulateString"));
	VBox simulationStyles = new VBox(VERTICAL_SPACING, simulationChoices,
		edgeChoices, shapeChoices, SIMULATE);
	return simulationStyles;
    }

    // make a simulate button
    private Button makeButton(String text) {
	Button simulateButton = new Button(text);
	simulateButton.setId("simulateButton");
	// handle click event
	simulateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent arg0) {
		PROGRAM_ENGINE.startSimulation(SIMULATION);  
	    }
	});
	simulateButton.setDisable(true);
	return simulateButton;
    }

    /**
     * 
     * @param defaultChoice: String that represents the default value for this combo box
     * @return A ComboBox bearing the default choice
     */
    private ComboBox<Object> makeComboBox(String defaultChoice) {
	ComboBox<Object> dropDownMenu = new ComboBox<>();
	dropDownMenu.setVisibleRowCount(5);
	dropDownMenu.setValue(defaultChoice);
	return dropDownMenu;
    }

    /**
     * Creates a drop down menu that changes the value of the instance 
     * variable @param TYPE upon selection. When a valid type is selected 
     * from the choices, the boolean @param VALID is flagged true. 
     * 
     * @return dropDownMenu: a drop down menu that lets the user choose a 
     * simulation to simulate
     */
    private ComboBox<Object> simulatorChooser() {
	String defaultPrompt = PROGRAM_ENGINE.resourceString("chooserPromptString");
	ComboBox<Object> dropDownMenu = makeComboBox(defaultPrompt);
	ObservableList<Object> simulationChoices = 
		FXCollections.observableArrayList(defaultPrompt);
	simulationChoices.addAll(PROGRAM_ENGINE.getSimulations());
	dropDownMenu.setItems(simulationChoices);
	dropDownMenu.setId("simulatorChooser");
	dropDownMenu.getSelectionModel().selectedIndexProperty()
	.addListener(new ChangeListener<Number>() {
	    @Override
	    public void changed(ObservableValue<? extends Number> arg0, 
		    Number arg1, Number arg2) {
		String selected = (String) simulationChoices.get((Integer) arg2);
		if (!selected.equals(defaultPrompt)) {
		    SIMULATION_VALID = true;
		    SIMULATION = selected;
		} else {
		    SIMULATION_VALID = false;
		}
	    }
	});
	return dropDownMenu;
    }
    
    /**
     * Creates a drop down menu that changes the value of the instance 
     * variable @param EDGE upon selection. 
     * 
     * @return dropDownMenu: a drop down menu that lets the user choose the
     * edge handling for the simulation
     */
    private ComboBox<Object> edgeChooser() {
	String defaultPrompt = PROGRAM_ENGINE.resourceString("edgePromptString");
	ComboBox<Object> dropDownMenu = makeComboBox(defaultPrompt);
	ObservableList<Object> simulationChoices = 
		FXCollections.observableArrayList(defaultPrompt);
	simulationChoices.addAll(PROGRAM_ENGINE.getEdgeHandling());
	dropDownMenu.setItems(simulationChoices);
	dropDownMenu.setId("simulatorChooser");
	dropDownMenu.getSelectionModel().selectedIndexProperty()
	.addListener(new ChangeListener<Number>() {
	    @Override
	    public void changed(ObservableValue<? extends Number> arg0, 
		    Number arg1, Number arg2) {
		String selected = (String) simulationChoices.get((Integer) arg2);
		if (!selected.equals(defaultPrompt)) {
		    EDGE_VALID = true;
		    EDGE = selected;
		} else {
		    EDGE_VALID = false;
		}
	    }
	});
	return dropDownMenu;
    }
    
    private VBox cellSizeOptions() {
	Label defaultPropmt = new Label(PROGRAM_ENGINE.resourceString("sizePromptString"));
	TextField cellSize = new TextField();
	VBox sizePanel = new VBox(LABEL_SPACING, defaultPropmt, cellSize);
	return sizePanel;
    }

    /**
     * Creates a drop down menu that changes the value of the instance 
     * variable @param SHAPE upon selection. 
     * 
     * @return dropDownMenu: a drop down menu that lets the user choose the
     * shape of the cells to animate
     */
    private ComboBox<Object> shapeChooser() {
	String defaultPrompt = PROGRAM_ENGINE.resourceString("shapePromptString");
	ComboBox<Object> dropDownMenu = makeComboBox(defaultPrompt);
	ObservableList<Object> simulationChoices = 
		FXCollections.observableArrayList(defaultPrompt);
	simulationChoices.addAll(PROGRAM_ENGINE.getShapes());
	dropDownMenu.setItems(simulationChoices);
	dropDownMenu.setId("simulatorChooser");
	dropDownMenu.getSelectionModel().selectedIndexProperty()
	.addListener(new ChangeListener<Number>() {
	    @Override
	    public void changed(ObservableValue<? extends Number> arg0, 
		    Number arg1, Number arg2) {
		String selected = (String) simulationChoices.get((Integer) arg2);
		if (!selected.equals(defaultPrompt)) {
		    SHAPE_VALID = true;
		    SHAPE = selected;
		} else {
		    SHAPE_VALID = false;
		}
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
	if (!SIMULATION.equals(DEFAULT_INDICATOR) && SIMULATE.isDisabled()) {
	    SIMULATE.setDisable(false);
	}
	else if (SIMULATION.equals(DEFAULT_INDICATOR) && !SIMULATE.isDisabled()) {
	    SIMULATE.setDisable(true);
	}
    }
    
    public String getShape() {
	return SHAPE;
    }

    public boolean getEdge() {
	if(EDGE.equals("finite")) return true;
	else return false;
    }
}

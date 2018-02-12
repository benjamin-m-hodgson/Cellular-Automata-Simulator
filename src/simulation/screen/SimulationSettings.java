package simulation.screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import simulation.Engine;

public class SimulationSettings extends Screen {

    private final int VERTICAL_SPACING = 20;
    private final int LABEL_SPACING = 5;
    private final double FRAMES_PER_SECOND = 120;
    private final long MILLISECOND_DELAY = Math.round(1000 / FRAMES_PER_SECOND);
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private final double CELL_MAX_SIZE = 50;
    private final double CELL_MIN_SIZE = 0.1;
    private final double SPACE_MAX_SIZE = 5;
    private final double SPACE_MIN_SIZE = 0;
    private final double INVALID_INDICATOR = -1;
    private String DEFAULT_INDICATOR;
    private String SIMULATION;
    private boolean SIMULATION_VALID;
    private String EDGE;
    private boolean EDGE_VALID;
    private String SHAPE;
    private boolean SHAPE_VALID;
    private String COLOR;
    private boolean COLOR_VALID;
    private TextField CELL_SIZE;
    private TextField SPACE_SIZE;
    private Button SIMULATE;
    private List<String> SHAPE_FIELDS = Arrays.asList(new String[] {
	    "Rectangle",
	    "Triangle"
    });
    private List<String> EDGE_HANDLING_FIELDS = Arrays.asList(new String[] {
	    "Finite",
	    "Toroidal"
    });

    public SimulationSettings(Engine programEngine) {
	super(programEngine);
	DEFAULT_INDICATOR = PROGRAM_ENGINE.resourceString("defaultString");
    }

    @Override
    public void makeRoot() {
	VBox simulationStyles = makeStylePanel();
	ROOT = simulationStyles;
	// attach "animation loop" to time line to play it
	KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
		e -> step(SECOND_DELAY));
	Timeline animation = new Timeline();
	animation.setCycleCount(Timeline.INDEFINITE);
	animation.getKeyFrames().add(frame);
	animation.play();     
    }

    /**
     * 
     * @return the Style Panel that contains all of the user input options
     */
    private VBox makeStylePanel() {
	ComboBox<Object> simulationChoices = simulatorChooser();
	ComboBox<Object> shapeChoices = shapeChooser();
	ComboBox<Object> edgeChoices = edgeChooser();
	ComboBox<Object> colorChoices = colorChooser();
	VBox cellSizing = cellSizeOptions();
	SIMULATE = makeButton(PROGRAM_ENGINE.resourceString("simulateString"));
	VBox simulationStyles = new VBox(VERTICAL_SPACING, simulationChoices,
		edgeChoices, shapeChoices, colorChoices, cellSizing, SIMULATE);
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
		// get cellSize from text field
		double cellSize;
		try {
		    cellSize = Double.parseDouble(CELL_SIZE.getText());
		}
		catch(Exception e) {
		    cellSize = INVALID_INDICATOR;
		}
		// get spaceSize from text field
		double spaceSize;
		try {
		    spaceSize = Double.parseDouble(SPACE_SIZE.getText());
		}
		catch(Exception e) {
		    spaceSize = INVALID_INDICATOR;
		}
		PROGRAM_ENGINE.startSimulation(SIMULATION, true, SHAPE, COLOR, 
			cellSize, spaceSize);  
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
	simulationChoices.addAll(getEdgeHandling());
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
		} 
		else {
		    EDGE_VALID = false;
		}
	    }
	});
	return dropDownMenu;
    }

    private VBox cellSizeOptions() {
	Label defaultPrompt = new Label(PROGRAM_ENGINE.resourceString("sizePromptString"));
	defaultPrompt.setId("simpleLabel");
	Label spacePrompt = new Label(PROGRAM_ENGINE.resourceString("spacePromptString"));
	spacePrompt.setId("simpleLabel");
	VBox sizePanel = new VBox(LABEL_SPACING);
	sizePanel.setId("sizePanel");
	HBox sizeOption = sizeOption(sizePanel);
	HBox spaceOption = spaceOption(sizePanel);
	sizePanel.getChildren().addAll(defaultPrompt, sizeOption, spacePrompt, spaceOption);
	return sizePanel;
    }

    private HBox spaceOption(Parent sizePanel) {
	SPACE_SIZE = numberField(sizePanel, SPACE_MIN_SIZE, SPACE_MAX_SIZE);
	SPACE_SIZE.setId("simulationTextField");
	Label maxPrompt = new Label(Double.toString(SPACE_MAX_SIZE));
	Label minPrompt = new Label(Double.toString(SPACE_MIN_SIZE));
	maxPrompt.setId("simpleLabel");
	minPrompt.setId("simpleLabel");
	HBox spaceOption = new HBox(LABEL_SPACING, minPrompt, SPACE_SIZE, maxPrompt);
	spaceOption.setId("optionLabels");
	spaceOption.setAlignment(Pos.CENTER);
	return spaceOption;
    }

    private HBox sizeOption(Parent sizePanel){
	CELL_SIZE = numberField(sizePanel, CELL_MIN_SIZE, CELL_MAX_SIZE);
	CELL_SIZE.setId("simulationTextField");
	Label maxPrompt = new Label(Double.toString(CELL_MAX_SIZE));
	Label minPrompt = new Label(Double.toString(CELL_MIN_SIZE));
	maxPrompt.setId("simpleLabel");
	minPrompt.setId("simpleLabel");
	HBox sizeOption = new HBox(LABEL_SPACING, minPrompt, CELL_SIZE, maxPrompt);
	sizeOption.setId("optionLabels");
	sizeOption.setAlignment(Pos.CENTER);
	return sizeOption;
    }

    private TextField numberField(Parent root, double min, double max) {
	TextField numberTextField = new TextField();
	numberTextField.setText(DEFAULT_INDICATOR);
	// clear when the mouse clicks on the text field
	numberTextField.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent arg0) {
		numberTextField.clear();
	    }
	});
	numberTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
	    @Override
	    public void handle(KeyEvent key) {
		if (key.getCode() == KeyCode.ENTER) {
		    // check input to make sure the value is within bounds
		    try {
			double sizeVal = Double.parseDouble(numberTextField.getText());
			if (sizeVal >= min && sizeVal <= max) {			    
			    numberTextField.setText(Double.toString(sizeVal));
			}
			else {
			    numberTextField.setText(DEFAULT_INDICATOR);
			}

		    }
		    catch(Exception e) {
			numberTextField.setText(DEFAULT_INDICATOR);
		    }
		    root.requestFocus();
		}
	    }
	});
	return numberTextField;
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
	simulationChoices.addAll(getShapes());
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
     * Creates a drop down menu that changes the value of the instance 
     * variable @param COLOR upon selection. 
     * 
     * @return dropDownMenu: a drop down menu that lets the user choose the
     * color pallette to use for the simulation
     */
    private ComboBox<Object> colorChooser() {
	String defaultPrompt = PROGRAM_ENGINE.resourceString("colorPromptString");
	ComboBox<Object> dropDownMenu = makeComboBox(defaultPrompt);
	ObservableList<Object> simulationChoices = 
		FXCollections.observableArrayList(defaultPrompt);
	simulationChoices.add(DEFAULT_INDICATOR);
	simulationChoices.addAll(PROGRAM_ENGINE.getDisplayColors());
	dropDownMenu.setItems(simulationChoices);
	dropDownMenu.setId("simulatorChooser");
	dropDownMenu.getSelectionModel().selectedIndexProperty()
	.addListener(new ChangeListener<Number>() {
	    @Override
	    public void changed(ObservableValue<? extends Number> arg0, 
		    Number arg1, Number arg2) {
		String selected = (String) simulationChoices.get((Integer) arg2);
		if (!selected.equals(defaultPrompt)) {
		    COLOR = selected;
		    PROGRAM_ENGINE.setColor(COLOR);
		    COLOR_VALID = true;
		} else {
		    COLOR_VALID = false;
		}
	    }
	});
	return dropDownMenu;
    }

    private void processInputs() {
	SIMULATE.setDisable(!(EDGE_VALID && SHAPE_VALID && SIMULATION_VALID && COLOR_VALID));
    }

    /**
     * Change properties of shapes to animate them. In this instance,
     * primarily checks to see if the selected simulation is valid and
     * updates the @param SIMULATE button to reflect @param VALID. 
     * 
     * @param elapsedTime: time since last animation update
     */
    private void step (double elapsedTime) {
	processInputs();
    }

    public boolean getEdge() {
	if(EDGE.equals("finite")) return true;
	else return false;
    }

    /**
     * Returns list of ways for grid edges to be handled
     */
    public ObservableList<String> getEdgeHandling(){
	ArrayList<String> options = new ArrayList<>();
	options.addAll(EDGE_HANDLING_FIELDS);
	ObservableList<String> retList = FXCollections.observableArrayList(options);
	return retList;
    }

    /**
     * Returns list of different shapes
     */
    public ObservableList<String> getShapes(){
	ArrayList<String> options = new ArrayList<>();
	options.addAll(SHAPE_FIELDS);
	ObservableList<String> retList = FXCollections.observableArrayList(options);
	return retList;
    }
}

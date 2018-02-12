package simulation.screen;

import factoryClasses.StyleFactory;
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
import simulation.CurrentSimulation;
import simulation.Engine;
import simulation.cell.Cell;
import simulation.grid.Grid;

/**
 * 
 * @author Benjamin Hodgson
 * @date 2/8/18
 * 
 * Class to generate the control panel to be displayed on the side of the simulation screen.
 * The control panel child nodes are held in a VBox object.
 */
public class SimulationSettingsPanel {

    private final int GROUP_SPACING = 50;
    private final int LABEL_SPACING = 5;
    private final double FRAMES_PER_SECOND = 120;
    private final long MILLISECOND_DELAY = Math.round(1000 / FRAMES_PER_SECOND);
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private final int STATE_MIN = 0;
    private final int STATE_MAX = 2;
    private VBox CONTROL_PANEL;
    private Engine PROGRAM_ENGINE;    
    private StyleFactory STYLE = new StyleFactory();
    private ComboBox<Object> PARAM_MENU;
    private boolean PARAM_VALID;
    private boolean NEW_VAL_VALID;
    private boolean X_VALID;
    private boolean Y_VALID;
    private String PARAM;
    private Button CHANGE_PARAM;
    private Button CHANGE_STATE;
    private TextField PARAM_FIELD;
    private TextField CURRENT_PARAM;
    private TextField xFIELD;
    private TextField yFIELD;
    private TextField CURRENT_STATE;
    private TextField DESIRED_STATE;


    public SimulationSettingsPanel(Engine programEngine) {
	PROGRAM_ENGINE = programEngine;
	CONTROL_PANEL = sidePanel();
	// attach "animation loop" to time line to play it
	KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
		e -> step(SECOND_DELAY));
	Timeline animation = new Timeline();
	animation.setCycleCount(Timeline.INDEFINITE);
	animation.getKeyFrames().add(frame);
	animation.play(); 
    }

    public VBox construct() {
	return CONTROL_PANEL;
    }

    /**
     * Method to create and return the side panel that contains information about the 
     * current simulation and gives the user some control buttons to manipulate
     * the simulation animation. 
     * 
     * @return sidePanel: the panel on the side with information and animation controls
     */
    private VBox sidePanel() {
	VBox paramMenu = makeMenu();
	VBox stateMenu = makeStateMenu();
	VBox sidePanel = new VBox(GROUP_SPACING,
		paramMenu, stateMenu);
	return sidePanel;
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
	Label changeParam = makeInfoLabel(PROGRAM_ENGINE.
		resourceString("updateParamPrompt"));
	PARAM_MENU = paramChooser();
	PARAM_MENU.setId("simulatorChooser");
	CHANGE_PARAM = makeParamChangerButton(PROGRAM_ENGINE.resourceString("applyString"));
	VBox changeParamMenu = new VBox(LABEL_SPACING, changeParam, PARAM_MENU);
	CURRENT_PARAM = currentParamField();
	PARAM_FIELD = paramField(changeParamMenu);
	changeParamMenu.getChildren().addAll(CURRENT_PARAM, PARAM_FIELD, CHANGE_PARAM);
	changeParamMenu.setAlignment(Pos.CENTER);
	return changeParamMenu;
    }

    /**
     * Creates the component of the side panel 
     * 
     * @return stateMenu: a VBox containing controls to change a specific cell's state
     */
    private VBox makeStateMenu() {
	Label changeParam = makeInfoLabel(PROGRAM_ENGINE.
		resourceString("updateStatePrompt"));
	Label cellPrompt = new Label(PROGRAM_ENGINE.resourceString("coordinatesPrompt"));
	cellPrompt.setId("simpleLabel");
	cellPrompt.setAlignment(Pos.CENTER);
	Grid currentGrid = PROGRAM_ENGINE.currentGrid();
	Label gridPrompt = new Label(PROGRAM_ENGINE.resourceString("gridSizeString")
		+ Integer.toString(currentGrid.getXSize()) + " x " 
		+ Integer.toString(currentGrid.getYSize()));
	gridPrompt.setId("simpleLabel");
	int xMax = PROGRAM_ENGINE.getGrid(PROGRAM_ENGINE.getSimulationName()).getXSize() - 1;
	int yMax = PROGRAM_ENGINE.getGrid(PROGRAM_ENGINE.getSimulationName()).getYSize() - 1;
	xFIELD = xField(0, xMax);
	yFIELD = yField(0, yMax);
	HBox coordinateBox = new HBox(LABEL_SPACING, xFIELD, yFIELD);
	coordinateBox.setAlignment(Pos.CENTER);
	coordinateBox.setId("optionLabels");
	Label statePrompt = new Label(PROGRAM_ENGINE.resourceString("statePrompt"));
	statePrompt.setId("simpleLabel");
	CURRENT_STATE = currentField();
	DESIRED_STATE = desiredField(coordinateBox, STATE_MIN, STATE_MAX);
	HBox stateBox = new HBox(LABEL_SPACING, CURRENT_STATE, DESIRED_STATE);
	stateBox.setAlignment(Pos.CENTER);
	stateBox.setId("optionLabels");
	CHANGE_STATE = makeStateChangerButton(PROGRAM_ENGINE.resourceString("applyString"));
	VBox changeStateMenu = new VBox(LABEL_SPACING, changeParam, cellPrompt, gridPrompt,
		coordinateBox, statePrompt, stateBox, CHANGE_STATE);
	changeStateMenu.setAlignment(Pos.CENTER);
	return changeStateMenu;

    }

    /**
     * Creates a drop down menu that changes the value of the instance 
     * variable @param TYPE upon selection. When a valid type is selected 
     * from the choices, the boolean @param VALID is flagged true. 
     * 
     * @return dropDownMenu: a drop down menu that lets the user choose a 
     * simulation to simulate
     */
    private ComboBox<Object> paramChooser() {
	ComboBox<Object> dropDownMenu = new ComboBox<Object>();
	dropDownMenu.setVisibleRowCount(5);
	String defaultChoice = PROGRAM_ENGINE.resourceString("chooserParamString");
	dropDownMenu.setValue(defaultChoice);
	ObservableList<Object> simulationChoices = 
		FXCollections.observableArrayList(defaultChoice);
	simulationChoices.addAll(STYLE.getParameters(PROGRAM_ENGINE.currentGrid().getType()));
	dropDownMenu.setItems(simulationChoices);
	dropDownMenu.setId("simulatorChooser");
	dropDownMenu.getSelectionModel().selectedIndexProperty()
	.addListener(new ChangeListener<Number>() {
	    @Override
	    public void changed(ObservableValue<? extends Number> arg0, 
		    Number arg1, Number arg2) {
		String selected = (String) simulationChoices.get((Integer) arg2);
		PARAM_VALID = !(selected.equals(defaultChoice)); 
		PARAM = selected;
	    }
	});
	return dropDownMenu;
    }

    /**
     * Creates a text field that takes input to change a parameter value
     * 
     * @return paramField: a text field that allows the user to input a new parameter value
     */
    private TextField paramField(Parent root) {
	TextField numberTextField = new TextField();
	numberTextField.setId("simulationTextField");
	numberTextField.setText(PROGRAM_ENGINE.resourceString("newValuePrompt"));
	// clear when the mouse clicks on the text field
	numberTextField.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent arg0) {
		numberTextField.clear();
		NEW_VAL_VALID = false;
	    }
	});
	numberTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
	    @Override
	    public void handle(KeyEvent key) {
		if (key.getCode() == KeyCode.ENTER) {
		    if (PARAM_VALID) {
			// check input to make sure the value is within bounds
			int min = 0;
			int max = 10;
			try {
			    double sizeVal = Double.parseDouble(numberTextField.getText());
			    if (sizeVal >= min && sizeVal <= max) {			    
				numberTextField.setText(Double.toString(sizeVal));
				NEW_VAL_VALID = true;
			    }
			    else {
				numberTextField.setText(PROGRAM_ENGINE.resourceString(
					"newValuePrompt"));
				NEW_VAL_VALID = false;
			    }

			}
			catch(Exception e) {
			    numberTextField.setText(PROGRAM_ENGINE.resourceString(
				    "newValuePrompt"));
			    NEW_VAL_VALID = false;
			}
		    }
		    root.requestFocus();
		}
	    }
	});
	return numberTextField;
    }

    /**
     * Creates a text field that takes integer only input to get the x coordinate of a cell 
     * in the simulation
     * 
     * @param min: the minimum x coordinate in the current simulation
     * @param max: the maximum x coordinate in the current simulation
     * @return xField: a text field that allows the user to input an x coordinate to define a cell
     */
    private TextField xField(int min, int max) {
	TextField numberTextField = new TextField();
	numberTextField.setId("simulationTextField");
	numberTextField.setText(PROGRAM_ENGINE.resourceString("xPrompt"));
	// clear when the mouse clicks on the text field
	numberTextField.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent arg0) {
		numberTextField.clear();
		X_VALID = false;
	    }
	});
	numberTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
	    @Override
	    public void handle(KeyEvent key) {
		if (key.getCode() == KeyCode.ENTER) {
		    // check input to make sure the value is within bounds
		    try {
			int sizeVal = Integer.parseInt(numberTextField.getText());
			if (sizeVal >= min && sizeVal <= max) {			    
			    numberTextField.setText(Integer.toString(sizeVal));
			    X_VALID = true;
			}
			else {
			    numberTextField.setText(PROGRAM_ENGINE.resourceString("xPrompt"));
			    X_VALID = false;
			}

		    }
		    catch(Exception e) {
			numberTextField.setText(PROGRAM_ENGINE.resourceString("xPrompt"));
			X_VALID = false;
		    }
		    yFIELD.requestFocus();
		}
	    }
	});
	return numberTextField;
    }

    /**
     * Creates a text field that takes integer only input to get the y coordinate of a cell 
     * in the simulation
     * 
     * @param min: the minimum y coordinate in the current simulation
     * @param max: the maximum y coordinate in the current simulation
     * @return yField: a text field that allows the user to input an y coordinate to define a cell
     */
    private TextField yField(int min, int max) {
	TextField numberTextField = new TextField();
	numberTextField.setId("simulationTextField");
	numberTextField.setText(PROGRAM_ENGINE.resourceString("yPrompt"));
	// clear when the mouse clicks on the text field
	numberTextField.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent arg0) {
		numberTextField.clear();
		Y_VALID = false;
	    }
	});
	numberTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
	    @Override
	    public void handle(KeyEvent key) {
		if (key.getCode() == KeyCode.ENTER) {
		    // check input to make sure the value is within bounds
		    try {
			int sizeVal = Integer.parseInt(numberTextField.getText());
			if (sizeVal >= min && sizeVal <= max) {			    
			    numberTextField.setText(Integer.toString(sizeVal));
			    Y_VALID = true;
			}
			else {
			    numberTextField.setText(PROGRAM_ENGINE.resourceString("yPrompt"));
			    Y_VALID = false;
			}

		    }
		    catch(Exception e) {
			numberTextField.setText(PROGRAM_ENGINE.resourceString("yPrompt"));
			Y_VALID = false;
		    }
		    DESIRED_STATE.requestFocus();
		}
	    }
	});
	return numberTextField;
    }

    /**
     * Creates a text field that simply displays the state of the currently defined cells 
     * 
     * @return a text field
     */
    private TextField currentField() {
	TextField numberTextField = new TextField();
	numberTextField.setId("simulationTextField");
	numberTextField.setText(PROGRAM_ENGINE.resourceString("currentStatePrompt"));
	// turn of current state field because user can't input current state
	numberTextField.setDisable(true);
	return numberTextField;
    }
    
    /**
     * Creates a text field that simply displays the state of the currently defined cells 
     * 
     * @return a text field
     */
    private TextField currentParamField() {
	TextField numberTextField = new TextField();
	numberTextField.setId("simulationTextField");
	numberTextField.setText(PROGRAM_ENGINE.resourceString("oldValuePrompt"));
	// turn of current state field because user can't input current state
	numberTextField.setDisable(true);
	return numberTextField;
    }

    /**
     * Creates a text field that takes integer only input to change the state of a cell in the 
     * current simulation
     * 
     * @param min: the minimum state value for this cell
     * @param max: the maximum state value for this cell
     * @return desiredField: a text field that allows the user to input a state 
     */
    private TextField desiredField(Parent root, int min, int max) {
	TextField numberTextField = new TextField();
	numberTextField.setId("simulationTextField");
	numberTextField.setText(PROGRAM_ENGINE.resourceString("newStatePrompt"));
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
			int sizeVal = Integer.parseInt(numberTextField.getText());
			if (sizeVal >= min && sizeVal <= max) {	
			    numberTextField.setText(Integer.toString(sizeVal));
			}
			else {
			    numberTextField.setText(
				    PROGRAM_ENGINE.resourceString("newStatePrompt"));
			}

		    }
		    catch(Exception e) {
			numberTextField.setText(
				PROGRAM_ENGINE.resourceString("newStatePrompt"));
		    }
		    root.requestFocus();
		}
	    }
	});
	return numberTextField;
    }

    /**
     * 
     * @param text: text to be displayed on the button
     * @return simulateButton: a button to begin the selected simulation
     */
    private Button makeParamChangerButton(String text) {
	Button changerButton = new Button(text);
	changerButton.setId("simulateButton");
	// handle click event
	changerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent arg0) {
		changeParamValue();
	    }
	});
	changerButton.setDisable(true);
	return changerButton;
    }

    /**
     * 
     * @param text: text to be displayed on the button
     * @return simulateButton: a button to begin the selected simulation
     */
    private Button makeStateChangerButton(String text) {
	Button changerButton = new Button(text);
	changerButton.setId("simulateButton");
	// handle click event
	changerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent arg0) {
		changeStateValue();
	    }
	});
	changerButton.setDisable(true);
	return changerButton;
    }


    private void processParamInputs() {
	CHANGE_PARAM.setDisable(!(PARAM_VALID && NEW_VAL_VALID));
	if (PARAM_VALID) {
	    CURRENT_PARAM.setText(Double.toString(STYLE.getParameter(PROGRAM_ENGINE, PARAM)));
	}
	else {
	    CURRENT_PARAM.setText(PROGRAM_ENGINE.resourceString("oldValuePrompt"));
	    PARAM_FIELD.setText(PROGRAM_ENGINE.resourceString("newValuePrompt"));
	}
    }

    private void processStateInputs() {
	CHANGE_STATE.setDisable(!(X_VALID && Y_VALID));
	if (X_VALID && Y_VALID) {
	    Grid currentGrid = PROGRAM_ENGINE.getGrid(PROGRAM_ENGINE.getSimulationName());
	    int xVal = Integer.parseInt(xFIELD.getText());
	    int yVal = Integer.parseInt(yFIELD.getText());
	    Cell currentCell = currentGrid.getCell(xVal, yVal);
	    CURRENT_STATE.setText(Integer.toString(currentCell.getState()));
	}
	else {
	    CURRENT_STATE.setText(PROGRAM_ENGINE.resourceString("currentStatePrompt"));
	}
    }

    private void changeParamValue() {
	if(NEW_VAL_VALID && PARAM_VALID) {
	    double newVal = Double.parseDouble(PARAM_FIELD.getText());
	    STYLE.setParameter(PROGRAM_ENGINE, PARAM, newVal);
	}
    }

    private void changeStateValue() {
	if(X_VALID && Y_VALID) {
	    Grid currentGrid = PROGRAM_ENGINE.getGrid(PROGRAM_ENGINE.getSimulationName());
	    int xVal = Integer.parseInt(xFIELD.getText());
	    int yVal = Integer.parseInt(yFIELD.getText());
	    Cell currentCell = currentGrid.getCell(xVal, yVal);
	    currentCell.changeState(Integer.parseInt(DESIRED_STATE.getText()));
	    CurrentSimulation cellSimulation = PROGRAM_ENGINE.getCurrentSimulation();
	    cellSimulation.colorCell(currentCell, xVal, yVal);
	}
    }

    /**
     * Change properties of shapes to animate them. In this instance,
     * primarily checks to see if the selected simulation is valid and
     * updates the @param SIMULATE button to reflect @param VALID. 
     * 
     * @param elapsedTime: time since last animation update
     */
    private void step (double elapsedTime) {
	processParamInputs();
	processStateInputs();
    }

}

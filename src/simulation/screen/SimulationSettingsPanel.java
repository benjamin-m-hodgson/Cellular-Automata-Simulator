package simulation.screen;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import simulation.Engine;
import simulation.factoryClasses.StyleFactory;

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

    private VBox CONTROL_PANEL;
    private Engine PROGRAM_ENGINE;

    private StyleFactory STYLE = new StyleFactory();
    private boolean PARAM_VALID;
    private boolean NEW_VAL_VALID;
    private String NEW_VAL;
    private String PARAM;
    private Button CHANGE;


    public SimulationSettingsPanel(Engine programEngine) {
	PROGRAM_ENGINE = programEngine;
	CONTROL_PANEL = sidePanel();
    }

    public VBox construct() {
	return CONTROL_PANEL;
    }

    /**
     * Updates the information displayed in the control panel. Primarily 
     * updates @param GENERATION to reflect the current generation
     * in the simulation and checks to see if another simulation was selected.
     */
    public void update() {

    }

    /**
     * Method to create and return the side panel that contains information about the 
     * current simulation and gives the user some control buttons to manipulate
     * the simulation animation. 
     * 
     * @return sidePanel: the panel on the side with information and animation controls
     */
    private VBox sidePanel() {
	VBox simulationMenu = makeMenu();
	VBox sidePanel = new VBox(GROUP_SPACING,
		simulationMenu);
	sidePanel.setId("simulateSidePanel");
	sidePanel.prefHeightProperty().bind(Bindings.divide(PROGRAM_ENGINE.sceneHeight(), 1.0));
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
	ComboBox<Object> simulationChoices = simulatorChooser();
	CHANGE = makeChangerButton(PROGRAM_ENGINE.resourceString("updateParamString"));
	VBox changeParamMenu = new VBox(LABEL_SPACING, changeParam, 
		simulationChoices, numberField(), CHANGE);
	return changeParamMenu;
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
	ComboBox<Object> dropDownMenu = new ComboBox<Object>();
	dropDownMenu.setVisibleRowCount(5);
	String defaultChoice = PROGRAM_ENGINE.resourceString("chooserParamString");
	dropDownMenu.setValue(defaultChoice);
	ObservableList<Object> simulationChoices = 
		FXCollections.observableArrayList(defaultChoice);
	simulationChoices.addAll(STYLE.getParameters(PROGRAM_ENGINE.currentGrid().getType()));
	dropDownMenu.setItems(simulationChoices);
	dropDownMenu.setValue(PROGRAM_ENGINE.getSimulationName());
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
    
    private TextField numberField() {
	TextField numberTextField = new TextField();
	numberTextField.setText("1");
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
			if (sizeVal >= 0 && sizeVal <= 1) {		
			    numberTextField.setText(Double.toString(sizeVal));
			    NEW_VAL = numberTextField.getText();
			    NEW_VAL_VALID = true;
			    processInputs();
			}
			else {
			    numberTextField.setText("1");
			}
		    }
		    catch(Exception e) {
			numberTextField.setText("1");
		    }
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
    private Button makeChangerButton(String text) {
	Button changerButton = new Button(text);
	changerButton.setId("simulateButton");
	// handle click event
	changerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent arg0) {
		changeValue();
	    }
	});
	changerButton.setDisable(true);
	return changerButton;
    }
    
    
    private void processInputs() {
	CHANGE.setDisable(!(PARAM_VALID && NEW_VAL_VALID));
    }
    
    
    private void changeValue() {
	if(NEW_VAL_VALID && PARAM_VALID) {
	    STYLE.setParameter(PROGRAM_ENGINE, PARAM, Double.parseDouble(NEW_VAL));
	}
    }

}

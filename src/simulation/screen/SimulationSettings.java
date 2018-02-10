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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import simulation.Engine;

public class SimulationSettings extends Screen {

    private final int VERTICAL_SPACING = 20;
    private final int FRAMES_PER_SECOND = 60;
    private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    
    private String TYPE;
    private boolean VALID;
    private Button SIMULATE;

    public SimulationSettings(Engine programEngine) {
        super(programEngine);
    }

    @Override
    public void makeRoot() {
        ComboBox<Object> simulationChoices = simulatorChooser();
        SIMULATE = makeButton(PROGRAM_ENGINE.resourceString("simulateString"));
        VBox simulationStyles = new VBox(VERTICAL_SPACING, simulationChoices,
                SIMULATE);
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
    private ComboBox<Object> simulatorChooser() {
        ComboBox<Object> dropDownMenu = new ComboBox<>();
        dropDownMenu.setVisibleRowCount(5);
        String defaultChoice = PROGRAM_ENGINE.resourceString("defaultChooserString");
        dropDownMenu.setValue(defaultChoice);
        ObservableList<Object> simulationChoices = 
                FXCollections.observableArrayList(defaultChoice);
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

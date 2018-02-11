package simulation.screen;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    
    private final int DEFAULT_SPACING = 20;
    
    public StartScreen(Engine programEngine) {
        super(programEngine);
    }

    // make the root for the start screen to display on application start up
    @Override
    public void makeRoot() {
        Label programTitle = makeLabel(PROGRAM_ENGINE.resourceString("programTitleString"));
        Button newSimulation = makeButton(PROGRAM_ENGINE.resourceString("newSimulateString"));
        VBox newRoot = new VBox(DEFAULT_SPACING, programTitle, newSimulation);
        newRoot.setId("startScreenRoot");
        ROOT = newRoot;
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
        simulateButton.setId("newSimulationButton");
        // handle click event
        simulateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                PROGRAM_ENGINE.styleSimulation();	
            }
        });
        return simulateButton;
    }
}

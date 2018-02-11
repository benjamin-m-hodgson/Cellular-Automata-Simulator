package simulation.screen;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import simulation.Engine;

/**
 * 
 * @author Katherine Van Dyk
 * @date 2/11/2018
 *
 * Creates the root object to be placed in the start up Scene. 
 * This consists of a program title, a drop down menu to select a 
 * simulation to simulate, and a simulate button to begin the simulation.
 *
 */
public class ErrorScreen extends Screen {
    
    private final int DEFAULT_SPACING = 20;
    private String ERROR;
    
    public ErrorScreen(Engine programEngine) {
        super(programEngine);
    }
    
    
    public void setError(String error) {
        ERROR = error;
        makeRoot();
    }

    // make the root for the start screen to display on application start up
    @Override
    public void makeRoot() {
        Label programTitle = makeLabel(ERROR);
        VBox newRoot = new VBox(DEFAULT_SPACING, programTitle);
        newRoot.setId("errorScreenRoot");
        ROOT = newRoot;
    }

    // assign the node Id and use css document to format
    public Label makeLabel(String text) {
        Label textLabel = new Label(text);
        textLabel.setId("errorLabel");
        return textLabel;
    }

}

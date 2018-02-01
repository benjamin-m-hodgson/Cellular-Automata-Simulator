package simulation.screen;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import simulation.Engine;

public class StartScreen extends Screen {
	
	private final String DEFAULT_NAME = "Cellular Automata";
	private final Engine PROGRAM_ENGINE;
	
	// need to save the Engine to call functions on button clicks
	public StartScreen(Engine programEngine) {
		PROGRAM_ENGINE = programEngine;
	}

	// make the root for the start screen to display on application start up
	@Override
	public void makeRoot() {
		Label programTitle = makeLabel(DEFAULT_NAME + " Simulator");
     	GridPane newRoot = new GridPane();
     	newRoot.setId("startScreenRoot");
     	newRoot.getChildren().add(programTitle);
		ROOT = newRoot;
	}
	
	// assign the node Id and use css document to format
	@Override
	public Label makeLabel(String text) {
		Label textLabel = new Label(text);
		textLabel.setId("startLabel");
		return textLabel;
		
	}

	@Override
	public Button makeButton(String text) {
		// TODO Auto-generated method stub
		return null;
	}

}

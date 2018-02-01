import javafx.application.Application;
import javafx.stage.Stage;
import simulation.Engine;

/** 
 * 
 * @author Benjamin Hodgson
 * @author Katherine Van Dyk
 * @author Michael Acker
 * @date 1/30/18
 *
 * The driver JavaFX program to start and animate a cellular automata simulation.
 */
public class Driver extends Application {
	// Idea: Get grids from controller, pass to engine via "setGrids" and "setRules methods

	private Engine programEngine = new Engine();

	/**
	 * Initialize the program and begin the animation loop 
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// display the menu screen
		// Idea for file parsing: have file controller access the files, call method to parse them, access grids from controller, give them to engine
		FileController fileControl = new FileController();
		fileControl.parseFiles();
		
		programEngine.startProgram(primaryStage);
		primaryStage.show();		
	}
	
	
	/**
     * Start the program
     */
    public static void main (String[] args) {
        launch(args);
    }
    
    
    
}
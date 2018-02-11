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

	private final int DEFAULT_HEIGHT = 600;
	private final int DEFAULT_WIDTH = 800;
	private Engine programEngine = new Engine();

	/**
	 * Initialize the program and begin the animation loop 
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		programEngine.startProgram(primaryStage, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		primaryStage.show();		
	}

	/**
	 * Start the program
	 */
	public static void main (String[] args) {
		launch(args);
	}
}

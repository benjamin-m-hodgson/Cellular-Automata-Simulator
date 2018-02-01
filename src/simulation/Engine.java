package simulation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulation.screen.StartScreen;

/**
 * 
 * @author Benjamin Hodgson
 * @author Katherine Van Dyk
 * @author Michael Acker
 * @date 1/30/18
 *
 */
public class Engine {
	
	private final int FRAMES_PER_SECOND = 60;
	private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private final String DEFAULT_STYLESHEET = 
    		Engine.class.getClassLoader().getResource("default.css").toExternalForm();
    
    private final String PROGRAM_TITLE;   
    
    private Stage PROGRAM_STAGE;
    private Scene PROGRAM_SCENE;

    // Give the program a title
	public Engine(String programTitle) {
		PROGRAM_TITLE = programTitle;
	}
	
	/**
	 * Initializes the animation time line and assigns instance variables
	 * 
	 * @param primaryStage: the Stage placed in the Application
	 */
	public void startProgram(Stage primaryStage, int width, int height) {
		PROGRAM_STAGE = primaryStage;
		PROGRAM_STAGE.setTitle(PROGRAM_TITLE);
		// attach "program loop" to time line to play it
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();	
        // attach a Scene to the primaryStage 
        generateStartScene(width, height);
	}
	
	/**
	 * 
	 * @param type: The type of simulation to start
	 */
	public void startSimulation(String type) {
		//System.out.println("Start simulation!");
	}
	
	/**
	 * 
	 * @return the Simulation titles to be displayed to the user
	 */
	public ObservableList<String> getSimulations() {
		ObservableList<String> retList = FXCollections.observableArrayList("a", "b", "c");
		return retList;
	}

	/**
	 * Calls the Screen object to generate a start screen to display 
	 * to the user upon application start up. Assigns the instance variable
	 * @param PROGRAM_SCENE to allow for easy root changes to change scenes. 
	 */
	private void generateStartScene(int width, int height) {
		Parent root = new StartScreen(this).getRoot();
		PROGRAM_SCENE = new Scene(root, width, height);	
		PROGRAM_SCENE.getStylesheets().add(DEFAULT_STYLESHEET);
		PROGRAM_STAGE.setScene(PROGRAM_SCENE);
	}
	
	/**
	 * Change properties of shapes to animate them 
	 * 
	 * @param elapsedTime: time since last animation update
	 */
    private void step (double elapsedTime) {
    	//System.out.printf("Stepping!\n");
    }
	
}

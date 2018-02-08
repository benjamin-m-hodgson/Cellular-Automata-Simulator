package simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulation.grid.Grid;
import simulation.ruleSet.Ruleset;
import simulation.screen.SimulationScreen;
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

	private final String DEFAULT_STYLESHEET = 
			Engine.class.getClassLoader().getResource("default.css").toExternalForm();
	private final ResourceBundle DEFAULT_RESOURCES = 
			ResourceBundle.getBundle("simulation.default");
	
	private final String PROGRAM_TITLE;   

	private double GENERATIONS_PER_SECOND = 1;
	private double MILLISECOND_DELAY = 1000 / GENERATIONS_PER_SECOND;
	private double SECOND_DELAY = 1.0 / GENERATIONS_PER_SECOND;

	private Timeline PROGRAM_TIMELINE;
	private Stage PROGRAM_STAGE;
	private Scene PROGRAM_SCENE;
	private String SIMULATION_TYPE;
	private String SHAPE_TYPE = "Triangle";
	private int GENERATION;
	private boolean SIMULATING;
	private CurrentSimulation SIMULATION;

	private Map<String, Grid> GRIDS;
	private Map<String, Ruleset> RULES;

	// Give the program a title
	public Engine() {
		PROGRAM_TITLE = resourceString("programTitleString");
		GRIDS = null;
		RULES = null;
	}

	/**
	 * Initializes the animation time line and assigns instance variables
	 * 
	 * @param primaryStage: the Stage placed in the Application
	 */
	public void startProgram(Stage primaryStage, int width, int height) {
		PROGRAM_STAGE = primaryStage;
		// initialize maps with values from XML files
		initializeMaps();
		PROGRAM_STAGE.setTitle(PROGRAM_TITLE);
		// attach "program loop" to time line to play it
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		PROGRAM_TIMELINE = new Timeline();
		PROGRAM_TIMELINE.setCycleCount(Timeline.INDEFINITE);
		PROGRAM_TIMELINE.getKeyFrames().add(frame);
		playAnimation();	
		// attach a Scene to the primaryStage 
		generateStartScene(width, height);
	}

	/**
	 * 
	 * @param type: The type of simulation to start
	 */
	public void startSimulation(String type) {
		//System.out.println("Start simulation!");
		if (SIMULATING) {
			stopSimulation();
		}
		// reset instance variables
		initializeSimulation(type);
		Parent root = new SimulationScreen(this, SIMULATION).getRoot();
		PROGRAM_SCENE.setRoot(root);
		playAnimation();
	}

	/**
	 * Performs one frame or step in the animation
	 */
	public void singleStep() {
		pauseAnimation();
		step(SECOND_DELAY);
	}

	/**
	 * Pauses the animation
	 */
	public void pauseAnimation() {
		PROGRAM_TIMELINE.pause();
	}

	/**
	 * Starts the animation
	 */
	public void playAnimation() {
		PROGRAM_TIMELINE.play();
	}

	/**
	 * Changes @param GENERATIONS_PER_SECOND to @param speed, which has the 
	 * effect of visually changing the speed of the simulation animation.
	 * 
	 * @param speed: new Generation Speed to use for the animation
	 */
	public void setGenerationSpeed(double speed) {
		GENERATIONS_PER_SECOND = speed;
		// update instance variables
		MILLISECOND_DELAY = 1000 / GENERATIONS_PER_SECOND;
		SECOND_DELAY = 1.0 / GENERATIONS_PER_SECOND;
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		PROGRAM_TIMELINE.stop();
		PROGRAM_TIMELINE.getKeyFrames().setAll(frame);
		playAnimation();
	}

	/**
	 * Loops through the @param GRIDS HashMap to find all of the String types that
	 * map to grid objects and then checks to ensure they have a corresponding rule
	 * set by making sure the same String type is a key in the @param RULES HashMap
	 * to determine the valid simulations
	 * 
	 * @return the Simulation titles to be displayed to the user
	 */
	public ObservableList<String> getSimulations() {
		List<String> typeList = new ArrayList<String>();
		for (String type : GRIDS.keySet()) {
			if (RULES.containsKey(type)) {
				typeList.add(type);
			}
		}
		ObservableList<String> retList = FXCollections.observableArrayList(typeList);
		return retList;
	}

	/**
	 * Initializes the values in the maps GRIDS and RULES
	 */
	public void initializeMaps() {
		FileController filecontrol = new FileController();
		filecontrol.parseFiles();
		Map<String, Grid> grids = filecontrol.getGrid();
		Map<String, Ruleset> rules = filecontrol.getRules();
		for (String key : rules.keySet()) {
			Grid g = grids.get(key);
			rules.get(key).setGrid(g);
		}
		RULES = rules;
		GRIDS = grids;
	}

	/**
	 * 
	 * @return SIMULATION_TYPE: the current simulation being animated 
	 */
	public String getSimulationType() {
		return SIMULATION_TYPE;
	}

	/**
	 * 
	 * @return GENERATION: the current generation number in the simulation
	 */
	public int getGeneration() {
		return GENERATION;
	}
	
	public String resourceString(String key) {
		return DEFAULT_RESOURCES.getString(key);
	}

	/**
	 * Sets grids
	 */
	public void setGrids(HashMap<String, Grid> grids) {
		GRIDS = grids;
	}

	/**
	 * Sets rules
	 */
	public void setRules(HashMap<String, Ruleset> rules) {
		for (String key : rules.keySet()) {
			Grid g = getGrid(key);
			rules.get(key).setGrid(g);
		}

		RULES = rules;
	}
	
	public Grid currentGrid() {
		return getGrid(SIMULATION_TYPE);
	}
	
	public Ruleset currentRules() {
		return RULES.get(SIMULATION_TYPE);
	}
	
	public String currentShapeType() {
		return SHAPE_TYPE;
	}
	
	public ReadOnlyDoubleProperty sceneWidth() {
		return PROGRAM_STAGE.widthProperty();
	}
	
	public ReadOnlyDoubleProperty sceneHeight() {
		return PROGRAM_STAGE.heightProperty();
	}
	
	private void initializeSimulation(String type) {
		// reset instance variables
		SIMULATION_TYPE = type;
		SIMULATING = true;
		GENERATION = 0;
		initializeMaps();
		PROGRAM_STAGE.setTitle(SIMULATION_TYPE);
		if (SIMULATION != null) {
			SIMULATION.reset();
		}
		else {
			SIMULATION = new CurrentSimulation(this);
		}
	}


	/**
	 * 
	 * @param name: the key that maps to a Grid object in the map @param GRIDS.
	 * @return the Grid object @param name maps to. 
	 */
	public Grid getGrid(String name) {
		Grid cloneGrid = null;
		try {
			cloneGrid = (Grid) GRIDS.get(name);
			return cloneGrid;
		} catch ( NullPointerException e) {
			System.out.printf("Could not get Grid object with key %s\n", name);
		}
		return cloneGrid;
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
	 * Stops and clears the current animation, resetting instance variables
	 * to their default values.
	 */
	private void stopSimulation() {
		SIMULATING = false;
		GENERATIONS_PER_SECOND = 1;
		// update instance variables
		MILLISECOND_DELAY = 1000 / GENERATIONS_PER_SECOND;
		SECOND_DELAY = 1.0 / GENERATIONS_PER_SECOND;
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		PROGRAM_TIMELINE.stop();
		PROGRAM_TIMELINE.getKeyFrames().setAll(frame);
	}

	/**
	 * Change properties of shapes to animate them 
	 * 
	 * @param elapsedTime: time since last animation update
	 */
	private void step (double elapsedTime) {   	
		if (SIMULATING) {
			SIMULATION.update();
			GENERATION++;
		}
	}
}

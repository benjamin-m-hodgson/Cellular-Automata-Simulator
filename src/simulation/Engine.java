package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.transform.TransformerConfigurationException;

import configuration.FileController;
import configuration.XMLWriting.XMLWriter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import simulation.factoryClasses.ColorMapper;
import simulation.factoryClasses.StyleFactory;
import simulation.grid.Grid;
import simulation.ruleSet.Ruleset;
import simulation.screen.SimulationScreen;
import simulation.screen.SimulationSettings;
import simulation.screen.StartScreen;

/**
 * Holds instance of current animation
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
    private Timeline PROGRAM_TIMELINE;
    private Stage PROGRAM_STAGE;
    private Scene PROGRAM_SCENE; 

    private double GENERATIONS_PER_SECOND = 1;
    protected double MILLISECOND_DELAY = 1000 / GENERATIONS_PER_SECOND;
    protected double SECOND_DELAY = 1.0 / GENERATIONS_PER_SECOND;
    private int GENERATION;

    private String SIMULATION_NAME;
    private boolean SIMULATING;
    private CurrentSimulation SIMULATION;
    private Map<String, Grid> GRIDS;
    private Map<String, Ruleset> RULES;
    private String[] COLORS;

    /**
     * Constructor for Engine class, creates Title string
     */
    public Engine() {
	PROGRAM_TITLE = resourceString("programTitleString");
	GRIDS = null;
	RULES = null;
    }

    /**
     * Initializes the animation time line and assigns instance variables
     * 
     * @param primaryStage: the Stage placed in the Application
     * @param w: scene width
     * @param h: scene height 
     */
    public void startProgram(Stage primaryStage, int width, int height) {
	KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
		e -> step(SECOND_DELAY));
	PROGRAM_STAGE = primaryStage;
	PROGRAM_STAGE.setResizable(false);
	PROGRAM_STAGE.initStyle(StageStyle.UTILITY);
	PROGRAM_STAGE.setTitle(PROGRAM_TITLE);
	PROGRAM_TIMELINE = new Timeline();
	PROGRAM_TIMELINE.setCycleCount(Timeline.INDEFINITE);
	PROGRAM_TIMELINE.getKeyFrames().add(frame);
	initializeMaps();
	playAnimation();	
	generateStartScene(width, height);
    }

    /**
     * Presents a screen to the user that allows them to style the simulation
     */
    public void styleSimulation() {
	Parent root =  new SimulationSettings(this).getRoot();
	root.setId("simulationSettingsRoot");
	PROGRAM_SCENE.setRoot(root);
    }

    /**
     * Starts new simulation screen with given grid edge type @param edge and shape style @param shape
     * 
     * @param name: The name of the current simulation
     */
    public void startSimulation(String type, boolean edge, String shape, String color,
	    double size, double space) {
	if (SIMULATING) {
	    stopSimulation();
	}
	initializeSimulation(type, edge, shape, color, size, space);
	Parent root = new SimulationScreen(this, SIMULATION).getRoot();
	PROGRAM_SCENE.setRoot(root);
	playAnimation();
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
	    if (RULES.containsKey(type)) typeList.add(type);
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
	RULES = filecontrol.getRules();
	GRIDS = filecontrol.getGrids();
	setRules();
    }

    /**
     * Sets rules
     */
    public void setRules() {
	for (String key : RULES.keySet()) {
	    Grid g = getGrid(key);
	    RULES.get(key).setGrid(g);
	}
    }

    /**
     * Resets all instance variables when simulation called @param name  begins
     * 
     * @param shape: shape style to be displayed
     * @param edge: grid edge type
     */
    private void initializeSimulation(String name, boolean edge, String shape, String color,
	    double size, double space) {
	SIMULATION_NAME = name;
	SIMULATING = true;
	GENERATION = 0;
	initializeMaps();
	PROGRAM_STAGE.setTitle(SIMULATION_NAME);
	SIMULATION = new CurrentSimulation(this, edge, shape, color, size, space);
    }

    /**
     * Returns the grid corresponding to the key @param name 
     */
    public Grid getGrid(String name) {
	Grid cloneGrid = null;
	try {
	    cloneGrid = (Grid) GRIDS.get(name);
	    return cloneGrid;
	} catch (NullPointerException e) {
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
     * Initiates writing current grid to XML file
     */
    public void writeGridtoXML(String name) {
	XMLWriter writer = new XMLWriter();
	try {
	    writer.createDoc(currentGrid().getType(), name, currentGrid(), currentRules());
	} catch (TransformerConfigurationException e) {
	    System.out.println("Cannot write " + name + " to XML File.");
	}
    }

    /**
     * Returns resource string
     * 
     * @param key: desired string title
     * @return String: corresponding resource string
     */
    public String resourceString(String key) {
	return DEFAULT_RESOURCES.getString(key);
    }

    /**
     * Return the name of the current simulation being animated 
     */
    public String getSimulationName() {
	return SIMULATION_NAME;
    }

    /**
     * Return the name of the current simulation being animated 
     */
    public void setSimulationName(String s) {
	SIMULATION_NAME = s;
    }

    /**
     * Returns the current generation number in the simulation
     */
    public int getGeneration() {
	return GENERATION;
    }

    /**
     * Sets grids
     */
    public void setGrids(Map<String, Grid> grids) {
	GRIDS = grids;
    }

    /**
     * Gets state count
     */
    public int getStateCount(int state) {
	return currentGrid().stateCount(state);
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
     * @return current grid
     */
    public Grid currentGrid() {
	return getGrid(SIMULATION_NAME);
    }

    /**
     * @return current ruleset
     */
    public Ruleset currentRules() {
	return RULES.get(SIMULATION_NAME);
    }

    public CurrentSimulation getCurrentSimulation() {
	return SIMULATION;
    }

    /**
     * @return scene width
     */
    public ReadOnlyDoubleProperty sceneWidth() {
	return PROGRAM_STAGE.widthProperty();
    }

    /**
     * @return scene height
     */
    public ReadOnlyDoubleProperty sceneHeight() {
	return PROGRAM_STAGE.heightProperty();
    }

    /**
     * @return list of parameters specific to simulation
     */
    public List<String> getParameters() {
	return new StyleFactory().getParameters(currentGrid().getType());
    }

    /**
     * @return list of color options
     */
    public ObservableList<String> getDisplayColors() {
	ObservableList<String> retList = FXCollections.observableArrayList(new ColorMapper().getColorOptions());
	return retList;
    }

    /**
     * Change properties of shapes to animate them 
     */
    private void step (double elapsedTime) {     
	if (SIMULATING) {
	    SIMULATION.update();
	    GENERATION++;
	}
    }

    /**
     * Sets color scheme to @param scheme
     */
    public void setColor(String scheme) {
	ColorMapper colorMap = new ColorMapper();
	if(!scheme.equals("Default")){
	    COLORS = colorMap.getColors(scheme);
	}
    }

    /**
     * @return String[] representing color scheme of current simulation
     */
    public String[] getColors() {
	return COLORS;
    }
}

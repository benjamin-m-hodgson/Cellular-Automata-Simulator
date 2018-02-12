package simulation.screen.panels;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import simulation.Engine;

/**
 * 
 * @author Katherine Van Dyk
 * @date 2/8/18
 * 
 * Class to generate the control panel to be displayed on the side of the simulation screen.
 * The control panel child nodes are held in a VBox object.
 */
public class SimulationGraphPanel{

    private static final int MAX_DATA_POINTS = 25;
    private XYChart.Series<Number, Number> state0 = new XYChart.Series<>();
    private XYChart.Series<Number, Number> state1 = new XYChart.Series<>();
    private XYChart.Series<Number, Number> state2 = new XYChart.Series<>();

    private Engine PROGRAM_ENGINE;
    private HBox GRAPH_PANEL;
    private NumberAxis xAxis;
    private NumberAxis yAxis;

    /**
     * Constructor
     * 
     * @param programEngine
     */
    public SimulationGraphPanel(Engine programEngine) {
	PROGRAM_ENGINE = programEngine;
	GRAPH_PANEL = bottomPanel();
	state0.setName("State 0");
	state1.setName("State 1");
	state2.setName("State 2");
    }

    public HBox construct() {
	return GRAPH_PANEL;
    }

    /**
     * Method to create and return the side panel that contains information about the 
     * current simulation and gives the user some control buttons to manipulate
     * the simulation animation. 
     * 
     * @return sidePanel: the panel on the side with information and animation controls
     */
    private HBox bottomPanel() {
	HBox simulationInfo = new HBox(createChart());
	return simulationInfo;
    }

    private LineChart<Number, Number> createChart(){
	xAxis = new NumberAxis();
	yAxis = new NumberAxis();
	configureAxes(xAxis, yAxis);
	LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);
	configureChart(lineChart);
	return lineChart;
    }

    private void configureChart(LineChart<Number, Number> chart) {
	chart.setPrefHeight(100);
	chart.setPrefWidth(500);
	chart.setAnimated(false);
	chart.getData().add(state0);
	chart.getData().add(state1);
	chart.getData().add(state2);
    }

    private void configureAxes(NumberAxis x, NumberAxis y) {
	xAxis.setForceZeroInRange(false);
	xAxis.setAutoRanging(false);
	xAxis.setMinorTickVisible(false);
	xAxis.setLabel("Generation");
	yAxis.setForceZeroInRange(false);
    }

    private void addDataToSeries(int count0, int count1, int count2) {
	state0.getData().add(new XYChart.Data<Number, Number>(PROGRAM_ENGINE.getGeneration(), count0));
	state1.getData().add(new XYChart.Data<Number, Number>(PROGRAM_ENGINE.getGeneration(), count1));
	state2.getData().add(new XYChart.Data<Number, Number>(PROGRAM_ENGINE.getGeneration(), count2));
    }

    public void update() {
	int state0  = PROGRAM_ENGINE.getStateCount(0);
	int state1  = PROGRAM_ENGINE.getStateCount(1);
	int state2  = PROGRAM_ENGINE.getStateCount(2);
	xAxis.setLowerBound(PROGRAM_ENGINE.getGeneration() - MAX_DATA_POINTS);
	xAxis.setUpperBound(PROGRAM_ENGINE.getGeneration() - 1);
	addDataToSeries(state0, state1, state2);
    }

}


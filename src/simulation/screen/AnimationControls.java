package simulation.screen;

import java.text.DecimalFormat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import simulation.CurrentSimulation;
import simulation.Engine;

/**
 * 
 * @author Benjamin Hodgson
 * @date 2/8/2018
 *
 * Class to create the grid pane containing the animation control buttons.
 * Buttons included: play, pause, reset, step, animation speed slider
 */
public class AnimationControls {
    
    private final double SPEED_MIN = 0.25;
    private final double SPEED_MAX = 2;
   
    private GridPane CONTROL_PANE;
    private Engine PROGRAM_ENGINE;
    
    private Button PLAY;
    private Button PAUSE;
    private Label SPEED;
    
    public AnimationControls(Engine programEngine) {
        PROGRAM_ENGINE = programEngine;
        makeSettings();
    }
    
    public GridPane construct() {
        return CONTROL_PANE;
    }
    
    /**
     * Creates the section of the side panel with the play, pause, reset, step, and speed
     * animation controls. 
     * 
     * @return simulationSettings: the bottom section of the side panel with animation 
     * controls
     */
    private void makeSettings() {
        PLAY = makePlayButton();
        PAUSE = makePauseButton();
        Button resetButton = makeResetButton();
        Button stepButton = makeStepButton();
        Slider speedSlider = makeSlider();
        SPEED = makeSliderLabel();
        CONTROL_PANE.add(PLAY, 0, 0);
        CONTROL_PANE.add(PAUSE, 1, 0);
        CONTROL_PANE.add(resetButton, 0, 1);
        CONTROL_PANE.add(stepButton, 1, 1);
        CONTROL_PANE.add(speedSlider, 0, 2);
    }
    
    /**
     * Creates a play button to be displayed in the side panel to begin playing a paused
     * animation. 
     * 
     * @return playButton: the button to restart or play a paused simulation
     */
    private Button makePlayButton() {
        Button playButton = new Button();
        playButton.setId("playButton");
        playButton.setDisable(true);
        // handle click event
        playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                PAUSE.setDisable(false);
                PLAY.setDisable(true);
                PROGRAM_ENGINE.playAnimation();
            }
        });
        return playButton;
    }

    /**
     * Creates a pause button to be displayed in the side panel to pause playing an
     * animation. 
     * 
     * @return pauseButton: the button to pause a running simulation
     */
    private Button makePauseButton() {
        Button pauseButton = new Button();
        pauseButton.setId("pauseButton");
        pauseButton.setDisable(false);
        // handle click event
        pauseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                PAUSE.setDisable(true);
                PLAY.setDisable(false);
                PROGRAM_ENGINE.pauseAnimation();
            }
        });
        return pauseButton;
    }

    /**
     * Creates a reset button to be displayed in the side panel to reset a playing 
     * animation from the beginning with initial parameters and properties.  
     * 
     * @return resetButton: the button to reset a running simulation from its initial state
     */
    private Button makeResetButton() {
        Button resetButton = new Button();
        resetButton.setId("resetButton");
        // handle click event
        resetButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
        		CurrentSimulation simulation = PROGRAM_ENGINE.getCurrentSimulation();
                PROGRAM_ENGINE.startSimulation(PROGRAM_ENGINE.getSimulationName(), simulation.getShape(), simulation.getEdge());
            }
        });
        return resetButton;
    }

    /**
     * Creates a step button to be displayed in the side panel to step forward one frame 
     * in the animation.
     * 
     * @return stepButton: the button to step one frame through an animation.
     */
    private Button makeStepButton() {
        Button stepButton = new Button();
        stepButton.setId("stepButton");
        // handle click event
        stepButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                PROGRAM_ENGINE.singleStep();
                PAUSE.setDisable(true);
                PLAY.setDisable(false);
            }
        });
        return stepButton;
    }

    /**
     * Slider that adjusts the number of generations animated per second in the 
     * simulation.
     * 
     * @return speedSlider: a Slider to change the generation speed
     */
    private Slider makeSlider() {
        Slider speedSlider = new Slider(SPEED_MIN, SPEED_MAX, 1);
        speedSlider.setId("simulateSpeedSlider");
        speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, 
                    Number arg1, Number arg2) {
                PROGRAM_ENGINE.setGenerationSpeed((double) arg2);
                DecimalFormat twoPlaces = new DecimalFormat("#.00");
                String speedArgs = twoPlaces.format(arg2);
                String speedText = "Animation Speed: " + speedArgs;
                SPEED.setText(speedText);
            }
        });
        return speedSlider;
    }
    
    /**
     * Creates a Label object that displays the current speed of the animation with
     * respect to the number of generations processed per second.
     * 
     * @return sliderLabel: a Label indicating the current selected generation speed
     */
    private Label makeSliderLabel() {
        Label sliderLabel = new Label(PROGRAM_ENGINE.
                resourceString("animationSpeedString") + " 1.00");
        sliderLabel.setId("simulateSpeedLabel");
        return sliderLabel;
    }

}

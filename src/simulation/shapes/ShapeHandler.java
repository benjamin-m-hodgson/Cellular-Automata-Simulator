package simulation.shapes;

import javafx.beans.value.ObservableValue;
import simulation.Engine;

/**
 * 
 * @author Benjamin Hodgson
 * @date 2/9/2018
 * 
 * Class to determine a Shape's position in the Scene
 */
public abstract class ShapeHandler {
    
    private final double DEFAULT_INDICATOR = -1;
    private final double DEFAULT_SPACING = 0.5;   
    protected Engine PROGRAM_ENGINE;
    private double SPACING;

    /**
     * Class constructor, ensures object knows its engine and sets a cells
     * height and width and the amount of spacing between cells.
     * 
     * @param gameEngine: application engine
     */
    public ShapeHandler(Engine programEngine, double size, double spacing) {
        PROGRAM_ENGINE = programEngine;
        if (spacing == DEFAULT_INDICATOR) {
            SPACING = DEFAULT_SPACING;
        }
        else {
            SPACING = spacing;
        }
    }
    
    /**
     * Calculates the default value for a cell shape's height from the screen size
     * 
     * @return int: default cell height
     */
    public abstract ObservableValue<Double> calculateDefaultHeight();
    
    /**
     * Calculates the default value for a cell shape's width from the screen size
     * 
     * @return int: default cell width
     */
    public abstract ObservableValue<Double> calculateDefaultWidth();
    
    /**
     * Determines the height of the shape that represents the cell
     * 
     * @param double height: the desired height value to be used for the cell shape
     * @return the double height as an ObservableValue<Double> 
     */
    public abstract ObservableValue<Double> calculateHeight(double height);
    
    /**
     * Determines the height of the shape that represents the cell
     * 
     * @param double height: the desired height value to be used for the cell shape
     * @return the double height as an ObservableValue<Double> 
     */
    public abstract ObservableValue<Double> calculateWidth(double width);
    
    /**
     * 
     * @return SPACING: the spacing value to be used between cell shapes in the display
     */
    public double getSpacing() {
        return SPACING;
    }
}

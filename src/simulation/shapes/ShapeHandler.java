package simulation.shapes;

import javafx.beans.binding.DoubleBinding;
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
    protected Engine PROGRAM_ENGINE;
    
    private DoubleBinding DEFAULT_HEIGHT;
    private DoubleBinding DEFAULT_WIDTH;
    private double SPACING;
    private DoubleBinding HEIGHT;
    private DoubleBinding WIDTH;

    /**
     * Class constructor, ensures object knows its engine and sets a cells
     * height and width and the amount of spacing between cells.
     * 
     * @param gameEngine: application engine
     */
    public ShapeHandler(Engine programEngine, double height, double width, double spacing) {
        PROGRAM_ENGINE = programEngine;
        SPACING = spacing;
        // calculate and assign correct cell values
        DEFAULT_HEIGHT = calculateDefaultHeight();
        DEFAULT_WIDTH = calculateDefaultWidth();
        if (height == DEFAULT_INDICATOR || width == DEFAULT_INDICATOR) {
            HEIGHT = DEFAULT_HEIGHT;
            WIDTH = DEFAULT_WIDTH;
        }
        else { 
            HEIGHT = calculateHeight();
            WIDTH = calculateWidth();
        }
    }
    
    public DoubleBinding getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }
    
    public DoubleBinding getDefaultWidth() {
        return DEFAULT_WIDTH;
    }
    
    /**
     * Calculates the default value for a cell shape's height from the screen size
     * 
     * @return int: default cell height
     */
    public abstract DoubleBinding calculateDefaultHeight();
    
    /**
     * Calculates the default value for a cell shape's width from the screen size
     * 
     * @return int: default cell width
     */
    public abstract DoubleBinding calculateDefaultWidth();
    
    public abstract DoubleBinding calculateHeight();
    
    public abstract DoubleBinding calculateWidth();
    
    public double getSpacing() {
        return getSPACING();
    }

    public DoubleBinding getHeight() {
        return HEIGHT;
    }

    public DoubleBinding getWidth() {
        return WIDTH;
    }

    public double getSPACING() {
        return SPACING;
    }
    
    /*
    private Polygon drawTriangle(int row, int col) {
        Polygon cellShape = new Polygon();
        cellShape.getPoints().addAll(new Double[] {
                15.0, 0.0,
                0.0, 15.0,
                30.0, 15.0,
        });
        if (col % 2 != 0) {
            cellShape.setTranslateX(cellShape.getLayoutX());
            cellShape.setTranslateY(cellShape.getLayoutY());
            cellShape.getTransforms().add(new Rotate(180, 0, 0));
        }
        cellShape.setId("defaultCell");

        return cellShape;
    }*/
}

package simulation;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import simulation.grid.Grid;

/**
 * 
 * @author Benjamin Hodgson
 * @date 2/9/2018
 * 
 * Class to determine a Shape's position in the Scene
 */
public abstract class ShapeHandler {
    
    private final double DEFAULT_INDICATOR = -1;
    private final double HEIGHT_SCALING = 200;
    private final double WIDTH_SCALING = 400;


    private DoubleBinding DEFAULT_HEIGHT;
    private DoubleBinding DEFAULT_WIDTH;
    private Engine PROGRAM_ENGINE;
    private double SPACING;
    private DoubleBinding HEIGHT;
    private DoubleBinding WIDTH;

    /**
     * Class constructor, ensures object knows its engine and sets a cells
     * height and width and the amount of spacing between cells.
     * 
     * @param gameEngine: application engine
     */
    public ShapeHandler(Engine programEngine, double height, 
            double width, double spacing) {
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
    
    public abstract DoubleBinding calculateHeight();
    
    public abstract DoubleBinding calculateWidth();
    
      /**
     * Calculates the default value for a cell shape's height from the screen size
     * 
     * @return int: default cell height
     */
    private DoubleBinding calculateDefaultHeight() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        double numCells = currentGrid.getYSize();
        DoubleBinding paneHeight = Bindings.subtract(PROGRAM_ENGINE.sceneHeight(), HEIGHT_SCALING);
        DoubleBinding height = Bindings.divide(paneHeight, numCells);
        DoubleBinding retHeight = Bindings.subtract(height, SPACING);
        return retHeight;
    }

    /**
     * Calculates the default value for a cell shape's width from the screen size
     * 
     * @return int: default cell width
     */
    private DoubleBinding calculateDefaultWidth() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        double numCells = currentGrid.getXSize();
        DoubleBinding paneWidth = Bindings.subtract(PROGRAM_ENGINE.sceneWidth(), WIDTH_SCALING);
        DoubleBinding width = Bindings.divide(paneWidth, numCells);
        DoubleBinding retWidth = Bindings.subtract(width, SPACING);
        return retWidth;
    }


    public double getSpacing() {
        return SPACING;
    }

    public DoubleBinding getHeight() {
        return HEIGHT;
    }

    public DoubleBinding getWidth() {
        return WIDTH;
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

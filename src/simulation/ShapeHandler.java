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

    private final static int DEFAULT_SPACING = 1;
    private final static int DEFAULT_INDICATOR = -1;
    private final static double HEIGHT_SCALING = 70;
    private final static double WIDTH_SCALING = 270;
    
    private DoubleBinding DEFAULT_HEIGHT;
    private DoubleBinding DEFAULT_WIDTH;
    private Engine PROGRAM_ENGINE;
    private int SPACING;
    private DoubleBinding HEIGHT;
    private DoubleBinding WIDTH;

    /**
     * Class constructor, ensures object knows its engine and sets a cells
     * height and width and the amount of spacing between cells.
     * 
     * @param gameEngine: application engine
     */
    public ShapeHandler(Engine programEngine, double height, 
            double width, int spacing) {
        PROGRAM_ENGINE = programEngine;
        DEFAULT_HEIGHT = calculateDefaultHeight();
        DEFAULT_WIDTH = calculateDefaultWidth();
        SPACING = spacing;
        if (height == defaultIndicator() || width == defaultIndicator()) {
            HEIGHT = DEFAULT_HEIGHT;
            WIDTH = DEFAULT_WIDTH;
        }
        else { 
            HEIGHT = calculateHeight();
            WIDTH = calculateWidth();
        }
    }

    public static int defaultSpacing() {
        return DEFAULT_SPACING;
    }
    
    public static int defaultIndicator() {
        return DEFAULT_INDICATOR;
    }
    
    public DoubleBinding getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }
    
    public DoubleBinding getDefaultWidth() {
        return DEFAULT_WIDTH;
    }
    
    public static double getHeightScaling() {
        return HEIGHT_SCALING;
    }

    public static double getWidthScaling() {
        return WIDTH_SCALING;
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
        return height;
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
        //System.out.println(rawWidth);
        System.out.println(numCells*SPACING);
        System.out.println(paneWidth);
        System.out.println(numCells);
        System.out.println(width);
        return width;
    }

    public int getSpacing() {
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

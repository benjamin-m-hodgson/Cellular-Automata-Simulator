package simulation.shapes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.Rectangle;
import simulation.Engine;
import simulation.grid.Grid;

/**
 * Handles drawing rectangle shapes for grid based on grid dimensions
 *
 * @author Ben Hodgson
 * @date 2/9/18
 */
public class RectangleHandler extends ShapeHandler {
    
    private final double DEFAULT_INDICATOR = -1;
    private final double SCENE_HEIGHT = 400;
    private final double SCENE_WIDTH = 400;
    private Rectangle CELL_SHAPE;
    private double SIZE;
    private double WIDTH;
    private double HEIGHT;

    /**
     * Constructor for Rectangle Handler
     *
     * @param programEngine: current instance of program engine
     * @param height: height of shape
     * @param width: width of shape
     * @param spacing: spacing between shapes
     */
    public RectangleHandler(Engine programEngine, double size, double spacing) {
        super(programEngine, size, spacing);
        SIZE = size;
    }
    
    /**
     * Creates a Rectangle Shape object to represent the cell in the simulation on the display.
     * Takes the cells height and width components along with its row and column position in the
     * grid to calculate its position on the screen.
     * 
     * @param row: the cells row position in the grid
     * @param col: the cells column position in the grid
     * @return Rectangle: a Rectangle Shape
     */
    public Rectangle generateRectangle(int row, int col) {
        CELL_SHAPE = new Rectangle();
        if (SIZE == DEFAULT_INDICATOR) {
            CELL_SHAPE.heightProperty().bind(calculateDefaultHeight());
            CELL_SHAPE.widthProperty().bind(calculateDefaultWidth());
        }
        else {
            CELL_SHAPE.heightProperty().bind(calculateHeight(SIZE));
            CELL_SHAPE.widthProperty().bind(calculateWidth(SIZE));
        }
        double xPos = col*WIDTH + (col+1)*getSpacing();
        double yPos = row*HEIGHT + (row+1)*getSpacing();
        CELL_SHAPE.setX(xPos);
        CELL_SHAPE.setY(yPos);
        return CELL_SHAPE;
    }

    /**
     * Calculates height of shape based on number of cells in grid and scene height
     */
    @Override
    public ObservableValue<Double> calculateHeight(double size) {
	HEIGHT = size;
        ObservableValue<Double> height = new SimpleDoubleProperty(size).asObject(); 
        return height;
    }

    /**
     * Calculates width of shape based on number of cells in grid and scene height
     */
    @Override
    public ObservableValue<Double> calculateWidth(double size) {
	WIDTH = size;
	ObservableValue<Double> width = new SimpleDoubleProperty(size).asObject(); 
        return width;
    }
    
    /**
     * Calculates default height of shape based on scene height and window scaling
     */
    @Override
    public ObservableValue<Double> calculateDefaultHeight() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        double numCells = currentGrid.getYSize();
        double height = (SCENE_HEIGHT/numCells) - getSpacing();
        HEIGHT = height;
        ObservableValue<Double> retHeight = new SimpleDoubleProperty(height).asObject();
        return retHeight;
    }
    
    /**
     * Calculates default width of shape based on scene height and window scaling
     */
    @Override
    public ObservableValue<Double> calculateDefaultWidth() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        double numCells = currentGrid.getXSize();
        double width = (SCENE_WIDTH/numCells) - getSpacing();
        WIDTH = width;
        ObservableValue<Double> retWidth = new SimpleDoubleProperty(width).asObject();
        return retWidth;
    }
}

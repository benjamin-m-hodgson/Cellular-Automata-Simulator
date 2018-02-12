package simulation.shapes;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
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
    
    private final double HEIGHT_SCALING = 200;
    private final double WIDTH_SCALING = 400;
    private Rectangle CELL_SHAPE;

    /**
     * Constructor for Rectangle Handler
     *
     * @param programEngine: current instance of program engine
     * @param height: height of shape
     * @param width: width of shape
     * @param spacing: spacing between shapes
     */
    public RectangleHandler(Engine programEngine, double height, double width, double spacing) {
        super(programEngine, height, width, spacing);
    }
    
    /**
     * 
     * @param row
     * @param col
     * @return
     */
    public Rectangle generateRectangle(int row, int col) {
        CELL_SHAPE = new Rectangle();
        CELL_SHAPE.heightProperty().bind(getHeight());
        CELL_SHAPE.widthProperty().bind(getWidth());
        double xPos = col*getWidth().doubleValue() + (col+1)*getSpacing();
        double yPos = row*getHeight().doubleValue() + (row+1)*getSpacing();
        CELL_SHAPE.setX(xPos);
        CELL_SHAPE.setY(yPos);
        return CELL_SHAPE;
    }

    /**
     * Calculataes height of shape based on number of cells in grid and scene height
     */
    @Override
    public DoubleBinding calculateHeight() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        double numCells = currentGrid.getYSize();
        double defaultHeight = getDefaultHeight().doubleValue();
        double scaleFactor = defaultHeight / SHAPE_HEIGHT;
        DoubleBinding height = Bindings.divide(PROGRAM_ENGINE.sceneHeight(), 
                numCells*scaleFactor);
        return height;
    }

    /**
     * Calculates width of shape based on number of cells in grid and scene height
     */
    @Override
    public DoubleBinding calculateWidth() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        double numCells = currentGrid.getXSize();
        double defaultWidth = getDefaultWidth().doubleValue();
        double scaleFactor = defaultWidth / SHAPE_WIDTH;
        DoubleBinding width = Bindings.divide(PROGRAM_ENGINE.sceneWidth(), 
                numCells*scaleFactor);
        return width;
    }
    
    /**
     * Calculates default height of shape based on scene height and window scaling
     */
    @Override
    public DoubleBinding calculateDefaultHeight() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        double numCells = currentGrid.getYSize();
        DoubleBinding paneHeight = Bindings.subtract(PROGRAM_ENGINE.sceneHeight(), HEIGHT_SCALING);
        DoubleBinding height = Bindings.divide(paneHeight, numCells);
        DoubleBinding retHeight = Bindings.subtract(height, getSpacing());
        return retHeight;
    }
    
    /**
     * Calculates default width of shape based on scene height and window scaling
     */
    @Override
    public DoubleBinding calculateDefaultWidth() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        double numCells = currentGrid.getXSize();
        DoubleBinding paneWidth = Bindings.subtract(PROGRAM_ENGINE.sceneWidth(), WIDTH_SCALING);
        DoubleBinding width = Bindings.divide(paneWidth, numCells);
        DoubleBinding retWidth = Bindings.subtract(width, getSpacing());
        return retWidth;
    }
}

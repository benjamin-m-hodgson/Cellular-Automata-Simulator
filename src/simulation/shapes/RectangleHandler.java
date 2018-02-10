package simulation.shapes;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.shape.Rectangle;
import simulation.Engine;
import simulation.grid.Grid;

public class RectangleHandler extends ShapeHandler {
    
    private final double HEIGHT_SCALING = 200;
    private final double WIDTH_SCALING = 400;
   
    private Rectangle CELL_SHAPE;

    public RectangleHandler(Engine programEngine, double height, double width, double spacing) {
        super(programEngine, height, width, spacing);
    }
    
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

    // broken need to fix
    @Override
    public DoubleBinding calculateHeight() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        double numCells = currentGrid.getYSize();
        double defaultHeight = getDefaultHeight().doubleValue();
        double scaleFactor = defaultHeight / PROGRAM_ENGINE.currentShapeHeight();
        DoubleBinding height = Bindings.divide(PROGRAM_ENGINE.sceneHeight(), 
                numCells*scaleFactor);
        return height;
    }

    // broken need to fix
    @Override
    public DoubleBinding calculateWidth() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        double numCells = currentGrid.getXSize();
        double defaultWidth = getDefaultWidth().doubleValue();
        double scaleFactor = defaultWidth / PROGRAM_ENGINE.currentShapeWidth();
        DoubleBinding width = Bindings.divide(PROGRAM_ENGINE.sceneWidth(), 
                numCells*scaleFactor);
        return width;
    }
    
    @Override
    public DoubleBinding calculateDefaultHeight() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        double numCells = currentGrid.getYSize();
        DoubleBinding paneHeight = Bindings.subtract(PROGRAM_ENGINE.sceneHeight(), HEIGHT_SCALING);
        DoubleBinding height = Bindings.divide(paneHeight, numCells);
        DoubleBinding retHeight = Bindings.subtract(height, getSPACING());
        return retHeight;
    }
    
    @Override
    public DoubleBinding calculateDefaultWidth() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        double numCells = currentGrid.getXSize();
        DoubleBinding paneWidth = Bindings.subtract(PROGRAM_ENGINE.sceneWidth(), WIDTH_SCALING);
        DoubleBinding width = Bindings.divide(paneWidth, numCells);
        DoubleBinding retWidth = Bindings.subtract(width, getSPACING());
        return retWidth;
    }
}

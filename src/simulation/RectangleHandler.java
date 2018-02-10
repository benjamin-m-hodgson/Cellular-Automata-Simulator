package simulation;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.shape.Rectangle;
import simulation.grid.Grid;

public class RectangleHandler extends ShapeHandler {
    
    private Engine PROGRAM_ENGINE;
    private Rectangle CELL_SHAPE;
    private int ROW;
    private int COLUMN;

    public RectangleHandler(Engine programEngine, double height, double width, double spacing) {
        super(programEngine, height, width, spacing);
    }
    public Rectangle generateRectangle(int row, int col) {
        CELL_SHAPE = new Rectangle();
        ROW = row;
        COLUMN = col;
        CELL_SHAPE.heightProperty().bind(getHeight());
        CELL_SHAPE.widthProperty().bind(getWidth());
        double xPos = ROW*getHeight().doubleValue() + (ROW+1)*getSpacing();
        double yPos = COLUMN*getWidth().doubleValue() + (COLUMN+1)*getSpacing();
        System.out.printf("Rectangle width %f, x pos: %f%n", CELL_SHAPE.getWidth(), xPos);
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
}

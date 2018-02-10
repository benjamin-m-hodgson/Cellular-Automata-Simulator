package simulation.shapes;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.shape.Polygon;
import simulation.Engine;
import simulation.grid.Grid;

public class TriangleHandler extends ShapeHandler {
    
    private final double HEIGHT_SCALING = 200;
    private final double WIDTH_SCALING = 400;
    
    private Engine PROGRAM_ENGINE;

    public TriangleHandler(Engine programEngine, double height, double width, double spacing) {
        super(programEngine, height, width, spacing);
    }
    
    public Polygon generateTriangle(int row, int col) {
        Polygon cellShape = new Polygon();
        double xTip = ((col + 1)*getHeight().doubleValue()) / 2 
                + (col+1)*getSpacing();
        double yTip = col*getWidth().doubleValue() + (col+1)*getSpacing();
              
        cellShape.getPoints().addAll(new Double[] {
                15.0, 0.0,
                0.0, 15.0,
                30.0, 15.0,
        });
        /*
        if (col % 2 != 0) {
            cellShape.setTranslateX(cellShape.getLayoutX());
            cellShape.setTranslateY(cellShape.getLayoutY());
            cellShape.getTransforms().add(new Rotate(180, 0, 0));
        }*/
        cellShape.setId("defaultCell");

        return cellShape;
    }

    @Override
    public DoubleBinding calculateHeight() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DoubleBinding calculateWidth() {
        // TODO Auto-generated method stub
        return null;
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

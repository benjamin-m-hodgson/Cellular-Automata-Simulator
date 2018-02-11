package simulation.shapes;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.shape.Polygon;
import simulation.Engine;
import simulation.grid.Grid;

public class TriangleHandler extends ShapeHandler {

    private final double HEIGHT_SCALING = 200;
    private final double WIDTH_SCALING = 400;
    private final double TRIANGLE_SCALING = 1.5;

    public TriangleHandler(Engine programEngine, double height, double width, double spacing) {
        super(programEngine, height, width, spacing);
    }

    public Polygon generateTriangle(int row, int col) {
        Polygon cellShape = new Polygon();
        if ((row + col) % 2 == 0) {
            upTriangle(cellShape, row, col);
        }
        else {
            downTriangle(cellShape, row, col);
        }
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
        DoubleBinding retHeight = Bindings.subtract(height, getSpacing());
        return retHeight;
    }

    @Override
    public DoubleBinding calculateDefaultWidth() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        double numCells = currentGrid.getXSize();
        DoubleBinding paneWidth = Bindings.subtract(PROGRAM_ENGINE.sceneWidth(), WIDTH_SCALING);
        DoubleBinding width = Bindings.divide(paneWidth, numCells);
        DoubleBinding retWidth = Bindings.subtract(width, getSpacing());
        DoubleBinding scaleWidth = Bindings.multiply(retWidth, TRIANGLE_SCALING);
        return scaleWidth;
    }

    // points listed (tip, left, right)
    private void upTriangle(Polygon triangle, int row, int col) {
        double xTip = ((col+1)*(getWidth().doubleValue() / 2) + (col+1)*getSpacing());
        double yTip = row*getHeight().doubleValue() + (row+1)*getSpacing();
        double xLeft = col*(getWidth().doubleValue() / 2) + (col+1)*getSpacing();
        double yLeft = (row+1)*getHeight().doubleValue() + (row+1)*getSpacing();
        double xRight = (col+2)*(getWidth().doubleValue() / 2) + (col+1)*getSpacing();
        double yRight = (row+1)*getHeight().doubleValue() + (row+1)*getSpacing();
        triangle.getPoints().addAll(new Double[] {
                xTip, yTip,
                xLeft, yLeft,
                xRight, yRight,
        });
    }

    // points listed (tip, left, right)
    private void downTriangle(Polygon triangle, int row, int col) {
        double xTip = ((col + 1)*(getWidth().doubleValue() / 2) + (col+1)*getSpacing());
        double yTip = (row+1)*getHeight().doubleValue() + (row+1)*getSpacing();
        double xLeft = col*(getWidth().doubleValue() / 2) + (col+1)*getSpacing();
        double yLeft = row*getHeight().doubleValue() + (row+1)*getSpacing();
        double xRight = (col+2)*(getWidth().doubleValue() / 2) + (col+1)*getSpacing();
        double yRight = row*getHeight().doubleValue() + (row+1)*getSpacing();
        triangle.getPoints().addAll(new Double[] {
                xTip, yTip,
                xLeft, yLeft,
                xRight, yRight,
        });
    }
}

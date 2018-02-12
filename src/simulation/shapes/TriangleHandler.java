package simulation.shapes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.Polygon;
import simulation.Engine;
import simulation.grid.Grid;

public class TriangleHandler extends ShapeHandler {

    private final double DEFAULT_INDICATOR = -1;
    private final double SCENE_HEIGHT = 400;
    private final double SCENE_WIDTH = 400;
    private final double TRIANGLE_SCALING = 1.5;
    private double SIZE;
    private double WIDTH;
    private double HEIGHT;

    public TriangleHandler(Engine programEngine, double size, double spacing) {
        super(programEngine, size, spacing);
        SIZE = size;
        if (SIZE == DEFAULT_INDICATOR) {
            calculateDefaultWidth();
            calculateDefaultHeight();
        }
        else {
            calculateWidth(size);
            calculateHeight(size);
        }
    }

    /**
     * Creates a Polygon Shape object to represent the cell in the simulation on the display.
     * Takes the cells height and width components along with its row and column position in the
     * grid to calculate its position on the screen.
     * 
     * @param row: the cells row position in the grid
     * @param col: the cells column position in the grid
     * @return Polygon: a Polygon shape, in this specific method this represents a Triangle
     */
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
    public ObservableValue<Double> calculateHeight(double size) {
	HEIGHT = size;
        ObservableValue<Double> height = new SimpleDoubleProperty(size).asObject(); 
        return height;
    }

    @Override
    public ObservableValue<Double> calculateWidth(double size) {
	WIDTH = size;
	ObservableValue<Double> width = new SimpleDoubleProperty(size).asObject(); 
        return width;
    }

    @Override
    public ObservableValue<Double> calculateDefaultHeight() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        double numCells = currentGrid.getYSize();
        double height = (SCENE_HEIGHT/numCells) - getSpacing();
        HEIGHT = height;
        ObservableValue<Double> retHeight = new SimpleDoubleProperty(height).asObject();
        return retHeight;
    }

    @Override
    public ObservableValue<Double> calculateDefaultWidth() {
        Grid currentGrid = PROGRAM_ENGINE.currentGrid();
        double numCells = currentGrid.getXSize();
        double width = (SCENE_WIDTH/numCells) - getSpacing();
        double scaleWidth = width*TRIANGLE_SCALING;
        WIDTH = scaleWidth;
        ObservableValue<Double> retWidth = new SimpleDoubleProperty(scaleWidth).asObject();
        return retWidth;
    }

    /**
     * Creates an upward facing Polygon Shape object to represent the cell in the simulation on 
     * the display. Takes the cells height and width components along with its row and 
     * column position in the grid to calculate its position on the screen. Upward facing means
     * the tip of this triangle is pointing towards the top of the screen. 
     * 
     * @param row: the cells row position in the grid
     * @param col: the cells column position in the grid
     * @return Polygon: a Polygon shape, in this specific method this represents a Triangle
     */
    private void upTriangle(Polygon triangle, int row, int col) {
        double xTip = ((col+1)*(WIDTH / 2) + (col+1)*getSpacing());
        double yTip = row*HEIGHT + (row+1)*getSpacing();
        double xLeft = col*(WIDTH / 2) + (col+1)*getSpacing();
        double yLeft = (row+1)*HEIGHT + (row+1)*getSpacing();
        double xRight = (col+2)*(WIDTH / 2) + (col+1)*getSpacing();
        double yRight = (row+1)*HEIGHT + (row+1)*getSpacing();
        triangle.getPoints().addAll(new Double[] {
                xTip, yTip,
                xLeft, yLeft,
                xRight, yRight,
        });
    }

    /**
     * Creates a downward facing Polygon Shape object to represent the cell in the simulation on 
     * the display. Takes the cells height and width components along with its row and 
     * column position in the grid to calculate its position on the screen. Upward facing means
     * the tip of this triangle is pointing towards the bottom of the screen. 
     * 
     * @param row: the cells row position in the grid
     * @param col: the cells column position in the grid
     * @return Polygon: a Polygon shape, in this specific method this represents a Triangle
     */
    private void downTriangle(Polygon triangle, int row, int col) {
        double xTip = ((col + 1)*(WIDTH / 2) + (col+1)*getSpacing());
        double yTip = (row+1)*HEIGHT + (row+1)*getSpacing();
        double xLeft = col*(WIDTH / 2) + (col+1)*getSpacing();
        double yLeft = row*HEIGHT + (row+1)*getSpacing();
        double xRight = (col+2)*(WIDTH / 2) + (col+1)*getSpacing();
        double yRight = row*HEIGHT + (row+1)*getSpacing();
        triangle.getPoints().addAll(new Double[] {
                xTip, yTip,
                xLeft, yLeft,
                xRight, yRight,
        });
    }
}

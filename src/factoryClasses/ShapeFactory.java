package factoryClasses;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import simulation.Engine;
import simulation.shapes.RectangleHandler;
import simulation.shapes.TriangleHandler;

/**
 * Handles constructing different shapes for display on grid
 * 
 * @author Ben Hodgson
 * @author Katherine Van Dyk
 *
 */
public class ShapeFactory {

    private final String RECTANGLE = "Rectangle";
    private final String TRIANGLE = "Triangle";
    private final double DEFAULT_SPACING = 0.5;
    private final double DEFAULT_INDICATOR = -1;
    private double SHAPE_HEIGHT = -1;
    private double SHAPE_WIDTH = -1;
    private double SHAPE_SPACE = 1;
    private String SHAPE_TYPE = "rectangle";

    /**
     * Chooses shape type to display on grid
     * 
     * @param r: row position of shape
     * @param c: column position of shape
     * @param currentShape: Shape object to display on screen
     */
    public Shape chooseShape(int r, int c, Engine PROGRAM_ENGINE) {
	if (SHAPE_TYPE.equalsIgnoreCase(RECTANGLE)) {
	    RectangleHandler shapeHandler = new RectangleHandler(PROGRAM_ENGINE,
		    DEFAULT_INDICATOR, DEFAULT_INDICATOR, DEFAULT_SPACING);
	    Rectangle cellShape = shapeHandler.generateRectangle(r, c);
	    cellShape.setId("defaultCell");
	    return cellShape;
	}
	else if (SHAPE_TYPE.equalsIgnoreCase(TRIANGLE)) {
	    TriangleHandler shapeHandler = new TriangleHandler(PROGRAM_ENGINE,
		    DEFAULT_INDICATOR, DEFAULT_INDICATOR, DEFAULT_SPACING);
	    Polygon cellShape = shapeHandler.generateTriangle(r, c);
	    cellShape.setId("defaultCell");
	    return cellShape;

	}
	return null;
    }
    
    /**
     * Returns current height of shape 
     */
    public double currentShapeHeight() {
	return SHAPE_HEIGHT;
    }

    /**
     * Returns current width of shape 
     */
    public double currentShapeWidth() {
	return SHAPE_WIDTH;
    }

    /**
     * Returns current shape space
     */
    public double currentShapeSpace() {
	return SHAPE_SPACE;
    }
    
    /**
     * Returns current shape type
     */
    public String currentShapeType() {
	return SHAPE_TYPE;
    }
    
    /**
     * Sets shape type
     */
    public void setShapeType(String s) {
	this.SHAPE_TYPE = s;
    }
}

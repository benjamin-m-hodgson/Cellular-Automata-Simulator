package simulation.shapes;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.shape.Polygon;
import simulation.Engine;

public class TriangleHandler extends ShapeHandler {
    
    private final double HEIGHT_SCALING = 200;
    private final double WIDTH_SCALING = 400;
    
    private Engine PROGRAM_ENGINE;

    public TriangleHandler(Engine programEngine, double height, double width, double spacing) {
        super(programEngine, height, width, spacing);
    }
    
    public Polygon generateTriangle(int row, int col) {
        return null;   
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DoubleBinding calculateDefaultWidth() {
        // TODO Auto-generated method stub
        return null;
    }

}

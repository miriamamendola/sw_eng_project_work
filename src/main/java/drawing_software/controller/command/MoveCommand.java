package drawing_software.controller.command;

import drawing_software.model.Shape;
import drawing_software.view.CanvasView;

import java.awt.geom.Point2D;

/**
 * Memorize the previous shape location, modified in Selection tool.
 */
public class MoveCommand implements Command {

    private final CanvasView canvas;
    private final Point2D oldShapeLocation;

    private final Shape selectedShape;

    public MoveCommand(CanvasView canvas, Shape selectedShape, Point2D oldShapeLocation) {
        this.canvas = canvas;
        this.selectedShape = selectedShape;
        this.oldShapeLocation = oldShapeLocation;
    }

    @Override
    public void execute() {
        updateTitle(canvas);
    }

    @Override
    public void undo() {
        if (selectedShape != null) {
            selectedShape.setLocation(oldShapeLocation);
            canvas.clearSelectedDrawable();
            canvas.repaint();
        }
    }
}

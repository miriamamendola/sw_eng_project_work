package drawing_software.controller.command;

import drawing_software.model.Drawable;
import drawing_software.view.CanvasView;

import java.awt.geom.Point2D;

/**
 * Memorize the previous shape location.
 */
public class MoveCommand implements Command {

    private final CanvasView canvas;
    private final Point2D oldShapeLocation;

    public MoveCommand(CanvasView canvas, Point2D oldShapeLocation) {
        this.canvas = canvas;
        this.oldShapeLocation = oldShapeLocation;
    }
    @Override
    public void execute() {

    }
}

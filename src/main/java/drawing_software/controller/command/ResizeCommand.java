package drawing_software.controller.command;

import drawing_software.model.Shape;
import drawing_software.view.Canvas;

import java.awt.*;
import java.awt.geom.Point2D;

public class ResizeCommand implements Command, Undoable {

    private final Canvas canvas;
    private final Point2D oldShapeLocation;
    private final Dimension oldShapeSize;

    private final Shape selectedShape;

    public ResizeCommand(Canvas canvas, Shape selectedShape, Point2D oldShapeLocation, Dimension oldShapeSize) {
        this.canvas = canvas;
        this.selectedShape = selectedShape;
        this.oldShapeLocation = oldShapeLocation;
        this.oldShapeSize = oldShapeSize;
    }


    @Override
    public void execute() {
        updateTitle(canvas);
    }

    @Override
    public void undo() {
        if (selectedShape != null) {
            selectedShape.setFrame(oldShapeLocation, oldShapeSize);
            canvas.clearSelectedDrawable();
            canvas.repaint();
        }
    }
}

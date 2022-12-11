package drawing_software.controller.command;

import drawing_software.model.Drawable;
import drawing_software.view.CanvasView;

/**
 * Allows to add a desired shape to the drawing, thus implementing a ConcreteCommand.
 */
public class ShapeCommand implements Command {

    private final CanvasView canvas;
    private final Drawable shape;

    /**
     * Allows the ConcreteCommand object to have a reference to the
     * receiver.
     *
     * @param canvas is the reference to the receiver which will perform the required action.
     * @param shape  is the shape to be added to the drawing.
     */
    public ShapeCommand(CanvasView canvas, Drawable shape) {
        this.canvas = canvas;
        this.shape = shape;
    }

    /**
     * Set the saved property to false (since the drawing has been altered and thus the current version is unsaved),
     * changes the title by adding the * (used for letting know the user that the drawing has been modified).
     * Adds the shape to the drawing by asking the receiver to perform the addDrawable() action.
     */
    @Override
    public void execute() {
        updateTitle(canvas);
        canvas.getDrawing().addDrawable(shape);
        canvas.repaint();
    }

    @Override
    public void undo() {

        if (shape != null) {
            canvas.getDrawing().removeDrawable(shape);
            canvas.clearSelectedDrawable();
            canvas.repaint();
        }

    }
}
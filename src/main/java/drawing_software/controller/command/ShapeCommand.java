package drawing_software.controller.command;

import drawing_software.Context;
import drawing_software.Main;
import drawing_software.model.Drawable;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.io.File;

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
        Context.getInstance().setSaved(false);
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(canvas);
        File f = Context.getInstance().getCurrentFile();
        if (f == null) {
            frame.setTitle("untitled" + " * - " + Main.appTitle);
        } else {
            frame.setTitle(f.getName() + " * - " + Main.appTitle);
        }

        canvas.getDrawing().addDrawable(shape);
    }

}
package src.controller.command;

import src.Context;
import src.Main;
import src.model.Drawable;
import src.view.CanvasView;

import javax.swing.*;
import java.io.File;

/**
 * Used for adding the shapes drawings.
 */
public class ShapeCommand implements Command {

    private final CanvasView canvas;
    private final Drawable shape;

    public ShapeCommand(CanvasView canvas, Drawable shape) {
        this.canvas = canvas;
        this.shape = shape;
    }

    /**
     * Set the saved property to false (because a modification is done),
     * changes the title by adding the * (used for letting know the user that the drawing is modified).
     * Adds the shape to the drawing.
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
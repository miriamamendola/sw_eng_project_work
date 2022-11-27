package project_work.controller.command;

import project_work.Context;
import project_work.Main;
import project_work.model.Drawable;
import project_work.view.CanvasView;

import javax.swing.*;
import java.io.File;

public class ShapeCommand implements Command {

    private final CanvasView canvas;
    private final Drawable shape;

    public ShapeCommand(CanvasView canvas, Drawable shape) {
        this.canvas = canvas;
        this.shape = shape;
    }

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
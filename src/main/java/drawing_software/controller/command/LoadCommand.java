package drawing_software.controller.command;

import drawing_software.Context;
import drawing_software.Main;
import drawing_software.model.Drawing;
import drawing_software.view.CanvasView;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Implements a ConcreteCommand class; in this case, the Command is the Load command.
 */
public class LoadCommand implements Command {

    private final CanvasView canvas;
    private final File file;

    /**
     * Allows the ConcreteCommand object to have a reference to the
     * receiver.
     *
     * @param canvas is the reference to the receiver which will perform the required action.
     * @param file   is the file to be loaded.
     */
    public LoadCommand(CanvasView canvas, File file) {
        this.canvas = canvas;
        this.file = file;
    }

    /**
     * First, it opens a windows that allows to choose the file to be loaded, and then loads
     * the selected file.
     * The operation is completed by adding the shapes in the file to the Drawing data structure
     * and then repainting the canvas in order to show such drawn shapes.
     */
    @Override
    public void execute() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            canvas.setDrawing((Drawing) ois.readObject());
            Context.getInstance().setSaved(true);

            Context.getInstance().setCurrentFile(file);

            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(canvas);
            frame.setTitle(file.getName() + " - " + Main.appTitle);

            canvas.repaint();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
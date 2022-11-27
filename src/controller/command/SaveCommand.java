package src.controller.command;

import src.Context;
import src.Main;
import src.view.CanvasView;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Encapsulates the saving logic by implementing the ConcreteCommand save.
 */
public class SaveCommand implements Command {

    private final CanvasView canvas;
    private final File file;

    /**
     * Allows the ConcreteCommand object to have a reference to the
     * receiver.
     *
     * @param canvas is the reference to the receiver which will perform the required action.
     * @param file   is the file to be saved.
     */
    public SaveCommand(CanvasView canvas, File file) {
        this.canvas = canvas;
        this.file = file;
    }

    /**
     * Saves the current drawing inside the file.
     * Updates the saved property and the currentFile property and
     * changes the current app title with the name of the saved file.
     * Implicitly removes the * (used for letting know the user that the drawing is modified).
     * The action that is required for the receiver to perform is the getDrawing() method.
     */
    @Override
    public void execute() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            Context.getInstance().setCurrentFile(file);
            Context.getInstance().setSaved(true);
            oos.writeObject(canvas.getDrawing());
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(canvas);
            frame.setTitle(file.getName() + " - " + Main.appTitle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
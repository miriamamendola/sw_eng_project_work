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
 * Encapsules the saving logic
 */
public class SaveCommand implements Command {

    private final CanvasView canvas;
    private final File file;

    public SaveCommand(CanvasView canvas, File file) {
        this.canvas = canvas;
        this.file = file;
    }

    /**
     * Saves the current drawing inside the file.
     * Updates the saved property and the currentFile property and
     * changes the current title with the name of the saved file.
     * Implicitly removes the * (used for letting know the user that the drawing is modified).
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
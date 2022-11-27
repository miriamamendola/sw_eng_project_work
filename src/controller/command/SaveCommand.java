package src.controller.command;

import src.Context;
import src.Main;
import src.view.CanvasView;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveCommand implements Command {

    private final CanvasView canvas;
    private final File file;

    public SaveCommand(CanvasView canvas, File file) {
        this.canvas = canvas;
        this.file = file;
    }

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
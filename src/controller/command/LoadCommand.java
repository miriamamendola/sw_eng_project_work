package src.controller.command;

import src.Context;
import src.Main;
import src.model.Drawing;
import src.view.CanvasView;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadCommand implements Command {

    private final CanvasView canvas;
    private final File file;

    public LoadCommand(CanvasView canvas, File file) {
        this.canvas = canvas;
        this.file = file;
    }

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
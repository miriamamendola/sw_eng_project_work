package project_work.controller;

import project_work.model.Drawing;
import project_work.view.CanvasView;

import java.io.*;

public class LoadCommand implements Command {

    private final CanvasView canvas;
    private File file;

    public LoadCommand(CanvasView canvas, File file) {
        this.canvas = canvas;
        this.file = file;
    }

    @Override
    public void execute() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            canvas.setDrawing((Drawing) ois.readObject());
            canvas.repaint();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
package project_work.controller;

import project_work.view.CanvasView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveCommand implements Command {

    private final CanvasView canvas;
    private File file;

    public SaveCommand(CanvasView canvas, File file) {
        this.canvas = canvas;
        this.file = file;
    }

    @Override
    public void execute() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(canvas.getDrawing());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
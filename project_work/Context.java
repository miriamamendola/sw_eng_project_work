package project_work;

import project_work.model.Drawing;

import java.io.File;

public class Context {

    private static Context instance = null;

    private Drawing currentDrawing;

    private File currentFile;

    private Context() {
        this.currentDrawing = new Drawing();
        this.currentFile = new File("untitled.draw");
    }

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }

        return instance;
    }

    public Drawing getCurrentDrawing() {
        return currentDrawing;
    }

    public void setCurrentDrawing(Drawing currentDrawing) {
        this.currentDrawing = currentDrawing;
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }
}

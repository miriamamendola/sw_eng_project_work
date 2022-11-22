package project_work;

import project_work.model.Drawing;

public class Context {

    private static Context instance = null;

    private Drawing currentDrawing;

    private Context(){
        this.currentDrawing = new Drawing();
    }

    public static Context getInstance(){
        if (instance == null){
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
}

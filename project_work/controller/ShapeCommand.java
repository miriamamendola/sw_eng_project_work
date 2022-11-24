package project_work.controller;

import project_work.model.Drawable;
import project_work.view.CanvasView;

public class ShapeCommand implements Command {

    private final CanvasView canvas;
    private final Drawable shape;

    public ShapeCommand(CanvasView canvas, Drawable shape){
        this.canvas = canvas;
        this.shape = shape;
    }

    @Override
    public void execute()  {
        canvas.getDrawing().addDrawable(shape);
    }

}
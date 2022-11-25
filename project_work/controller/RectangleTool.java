package project_work.controller;

import project_work.model.DrawableRectangle;
import project_work.view.CanvasView;

import java.awt.event.MouseEvent;

public class RectangleTool implements Tool {

    private DrawableRectangle rect;

    private final CanvasView canvas;

    private final Invoker invoker;

    public RectangleTool(CanvasView canvas, Invoker invoker){
        this.canvas = canvas;
        this.invoker = invoker;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        rect = new DrawableRectangle(mouseEvent.getX(), mouseEvent.getY());
        canvas.setDummyDrawable(rect);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        double width = mouseEvent.getX() - rect.getX();
        double height = mouseEvent.getY() - rect.getY();
        rect.setSize(width, height);
        canvas.repaint();
    }


    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (rect.getWidth() > 0 && rect.getHeight() > 0){
            invoker.executeCommand(new ShapeCommand(canvas, rect));
        }
        canvas.clearDummyDrawable();
    }

}

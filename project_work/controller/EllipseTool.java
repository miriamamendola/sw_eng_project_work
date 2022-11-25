package project_work.controller;

import project_work.model.DrawableEllipse;
import project_work.view.CanvasView;

import java.awt.event.MouseEvent;

public class EllipseTool implements Tool {

    private DrawableEllipse ellipse;

    private final CanvasView canvas;

    private final Invoker invoker;

    public EllipseTool(CanvasView canvas, Invoker invoker){
        this.canvas = canvas;
        this.invoker = invoker;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        double width = mouseEvent.getX() - ellipse.getX();
        double height = mouseEvent.getY() - ellipse.getY();
        ellipse.setSize(width, height);
        canvas.repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        ellipse = new DrawableEllipse(mouseEvent.getX(), mouseEvent.getY());
        canvas.setDummyDrawable(ellipse);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(ellipse.getWidth() > 0 && ellipse.getHeight() > 0){
            invoker.executeCommand(new ShapeCommand(canvas, ellipse));
        }

        canvas.clearDummyDrawable();
    }
}
